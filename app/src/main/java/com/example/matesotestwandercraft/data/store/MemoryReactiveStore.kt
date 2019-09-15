package com.example.matesotestwandercraft.data.store

import android.renderscript.Sampler
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import polanski.option.Option
import polanski.option.Option.ofObj
import polanski.option.function.Func1
import polanski.option.Option.none

import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.collections.List


class MemoryReactiveStore<Key, Value>(
    var extractKeyFromModel: Func1<Value, Key>,
    var cache: Store.MemoryStore<Key, Value>
) : ReactiveStore<Key, Value> {


    private lateinit var allSubject: Subject<Option<List<Value>>>

    private val subjectMap = HashMap<Key, Subject<Option<Value>>>()

    init {
        this.allSubject = PublishSubject.create<Option<List<Value>>>().toSerialized()
    }

    override fun storeSingular(model: Value) {
        val key = extractKeyFromModel.call(model)
        cache.putSingular(model)
        getOrCreateSubjectForKey(key).onNext(ofObj<Value>(model))
        // One item has been added/updated, notify to all as well
        val allValues = cache.getAll().map(({ ofObj(it) })).blockingGet(none())
        allSubject.onNext(allValues)
    }

    override fun storeAll(modelList: kotlin.collections.List<Value>) {
        cache.putAll(modelList)
        allSubject.onNext(ofObj<List<Value>>(modelList))
        // Publish in all the existing single item streams.
        // This could be improved publishing only in the items that changed. Maybe use DiffUtils?
        publishInEachKey()
    }

    override fun replaceAll(modelList: kotlin.collections.List<Value>) {
        cache.clear()
        storeAll(modelList)
    }


    override fun getSingular(key: Key): Observable<Option<Value>> {
        return Observable.defer<Option<Value>> { getOrCreateSubjectForKey(key).startWith(getValue(key)) }
            .observeOn(Schedulers.computation())
    }

    override fun getAll(): Observable<Option<List<Value>>> {
        return Observable.defer<Option<List<Value>>> { allSubject.startWith(getAllValues()) }
            .observeOn(Schedulers.computation())
    }

    private fun getValue(key: Key): Option<Value> {
        return cache.getSingular(key).map(({ ofObj(it) })).blockingGet(none())
    }

    private fun getAllValues(): Option<List<Value>> {
        return cache.getAll().map(({ ofObj(it) })).blockingGet(none())
    }

    private fun getOrCreateSubjectForKey(key: Key): Subject<Option<Value>> {
        synchronized(subjectMap) {
            return ofObj<Subject<Option<Value>>>(subjectMap[key]).orDefault({ createAndStoreNewSubjectForKey(key) })
        }
    }

    private fun createAndStoreNewSubjectForKey(key: Key): Subject<Option<Value>> {
        val processor = PublishSubject.create<Option<Value>>().toSerialized()
        synchronized(subjectMap) {
            subjectMap.put(key, processor)
        }
        return processor
    }

    /**
     * Publishes the cached data in each independent stream only if it exists already.
     */
    private fun publishInEachKey() {
        val keySet: Set<Key>
        synchronized(subjectMap) {
            keySet = HashSet<Key>(subjectMap.keys)
        }
        for (key in keySet) {
            val value = cache.getSingular(key).map(({ ofObj(it) })).blockingGet(none())
            publishInKey(key, value)
        }
    }

    /**
     * Publishes the cached value if there is an already existing stream for the passed key. The case where there isn't a stream for the passed key
     * means that the data for this key is not being consumed and therefore there is no need to publish.
     */
    private fun publishInKey(key: Key, model: Option<Value>) {
        val processor: Subject<Option<Value>>?
        synchronized(subjectMap) {
            processor = subjectMap[key]
        }
        ofObj(processor).ifSome { it.onNext(model) }
    }
}
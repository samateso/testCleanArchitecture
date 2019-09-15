package com.example.matesotestwandercraft.data.cache

import android.support.v7.util.ListUpdateCallback
import com.example.matesotestwandercraft.data.store.Store
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.Consumer
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers
import polanski.option.Option
import polanski.option.Option.none
import polanski.option.Option.ofObj
import java.util.concurrent.ConcurrentHashMap
import io.reactivex.functions.Function

class Cache<Key, Value> : Store.MemoryStore<Key, Value> {

    private val timestampProvider =  System.currentTimeMillis()

    private var extractKeyFromModel: Function<Value, Key>? = null

    private var itemLifespanMs: Option<Long>? = null

    private val cache = ConcurrentHashMap<Key, CacheEntry<Value>>()


    constructor(extractKeyFromModel: io.reactivex.functions.Function<Value, Key>
    ) {
//        this(extractKeyFromModel, timestampProvider, none<Long>())
    }

    constructor( extractKeyFromModel: Function<Value, Key>,
        timeoutMs: Long
    ) {
//        this(extractKeyFromModel, timestampProvider, ofObj(timeoutMs))
    }

    constructor(
        extractKeyFromModel: Function<Value, Key>,
        timeoutMs: Option<Long>
    ) {
        this.itemLifespanMs = timeoutMs
        this.extractKeyFromModel = extractKeyFromModel
    }

    override fun putSingular(value: Value) {
        Single.fromCallable { extractKeyFromModel!!.apply(value) }
            .subscribeOn(Schedulers.computation())
            .subscribe { key -> cache[key] = createCacheEntry(value) }
    }

    override fun putAll(values: List<Value>) {
//        Observable.fromIterable(values)
//            .toMap(extractKeyFromModel, Function<Value, V> { this.createCacheEntry(it) })
//            .subscribeOn(Schedulers.computation())
//            .subscribe(Consumer<Map<K, V>> { cache.putAll(it) })
        throw error("getSigular")
    }

    override fun getSingular(key: Key): Maybe<Value> {
//        return Maybe.fromCallable { cache.containsKey(key) }
//            .filter { isPresent -> isPresent }
//            .map<Any> { cache[key] }
//            .filter{ this.notExpired(it) }
//            .map{ CacheEntry.builder().cachedObject() }
//            .subscribeOn(Schedulers.computation())
        throw error("getSigular")
    }

    override fun getAll(): Maybe<List<Value>> {
        return Observable.fromIterable(cache.values)
           .filter{ this.notExpired(it) }
            .map{ t -> t.cachedObject() }
            .toList()
            .filter(Predicate<List<Value>> { it.isNotEmpty() })
            .subscribeOn(Schedulers.computation())
    }

    override fun clear() {
        cache.clear()
    }

    private fun createCacheEntry(value: Value): CacheEntry<Value> {
//        return CacheEntry.builder().cachedObject(value)
//            .creationTimestamp(timestampProvider)
//            .build()
        throw error("getSigular")
    }

    private fun notExpired(cacheEntry: CacheEntry<Value>): Boolean {
        return itemLifespanMs!!.match({ lifespanMs -> cacheEntry.creationTimestamp() + lifespanMs!! > timestampProvider },
            // When lifespan was not set the items in the cache never expire
            { true })
    }
}


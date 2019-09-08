package com.example.matesotestwandercraft.data

import android.support.annotation.NonNull
import io.reactivex.Flowable
import io.reactivex.Observable
import polanski.option.Option


public interface ReactiveStore<Key, Value> {
    fun storeSingular(model: Value)
    fun storeAll(modelList: List<Value>)
    fun replaceAll(modelList: List<Value>)
    fun getSingular(key: Key): Observable<Option<Value>>
    fun getAll(): Observable<Option<List<Value>>>
}
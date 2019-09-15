package com.example.matesotestwandercraft.data.store

import io.reactivex.Observable
import polanski.option.Option


interface ReactiveStore<Key, Value> {
    fun storeSingular(model: Value)
    fun storeAll(modelList: List<Value>)
    fun replaceAll(modelList: List<Value>)
    fun getSingular(key: Key): Observable<Option<Value>>
    fun getAll(): Observable<Option<List<Value>>>
}
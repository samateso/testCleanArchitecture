package com.example.matesotestwandercraft.data.store

import io.reactivex.Maybe

interface Store <Key, Value> {
    fun putSingular(value: Value)

    fun putAll(valueList: List<Value>)

    fun clear()

    fun getSingular(key: Key): Maybe<Value>

    fun getAll(): Maybe<List<Value>>

    interface MemoryStore<Key, Value> : Store<Key, Value>

    interface DiskStore<Key, Value> : Store<Key, Value>
}
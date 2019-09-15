package com.example.matesotestwandercraft.data.cache

import com.google.auto.value.AutoValue

@AutoValue
abstract class CacheEntry<T> {

    abstract fun cachedObject(): T

    abstract  fun creationTimestamp(): Long


    companion object {
        fun <T> builder(): Builder<T> {
            return AutoValue_CacheEntry.Builder()
        }
    }

    @AutoValue.Builder
    interface Builder<T> {

        fun cachedObject(`object`: T): Builder<T>

        fun creationTimestamp(creationTimestamp: Long): Builder<T>

        fun build(): CacheEntry<T>

    }
}
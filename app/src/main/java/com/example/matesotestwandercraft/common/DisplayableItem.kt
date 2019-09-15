package com.example.matesotestwandercraft.common

import com.google.auto.value.AutoValue

@AutoValue
abstract class DisplayableItem<T> {

    abstract fun type(): Integer?

    abstract fun model(): T?

    @AutoValue.Builder
    abstract class Builder<T> {

        abstract fun type(type: Integer): Builder<T>

        abstract fun model(model: T): Builder<T>

        abstract fun build(): DisplayableItem<T>
    }

    companion object {
        fun <T> builder(): Builder<T> {
            return AutoValue_DisplayableItem.Builder()
        }


        fun toDisplayableItem(model: Any, type: Integer): DisplayableItem<Any> {
            return DisplayableItem.builder<Any>().type(type).model(model).build()
        }
    }
}
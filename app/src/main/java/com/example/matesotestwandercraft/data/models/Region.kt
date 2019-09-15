package com.example.matesotestwandercraft.data.models

import com.google.auto.value.AutoValue
import io.reactivex.annotations.NonNull

@AutoValue
abstract class Region {

    @NonNull
    abstract fun nom(): String

    @NonNull
    abstract fun code(): String

    companion object {
        @NonNull
        fun builder(): Builder {
            return AutoValue_Region.Builder()
        }
    }

    @AutoValue.Builder
    interface Builder {

        fun nom(id: String): Builder

        fun code(code: String): Builder

        fun build(): Region
    }
}
package com.example.matesotestwandercraft.presentation

import com.google.auto.value.AutoValue
import io.reactivex.annotations.NonNull

@AutoValue
abstract class  DepartementViewModel {
    @NonNull
    abstract fun nom(): String

    @NonNull
    abstract fun code(): String

    //@NonNull
    //abstract fun region(): List<Region>


    companion object {
        @NonNull
        fun builder(): Builder {
            return AutoValue_DepartementViewModel.Builder()
        }
    }

    @AutoValue.Builder
    interface Builder {

        fun nom(nom: String): Builder

        fun code(code: String): Builder

        //fun region(region: List<Region>): Builder

        fun build(): DepartementViewModel
    }
}
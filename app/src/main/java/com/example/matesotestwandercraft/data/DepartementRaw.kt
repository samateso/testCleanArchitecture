package com.example.matesotestwandercraft.data

import com.example.matesotestwandercraft.data.models.Departement
import com.google.auto.value.AutoValue
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import io.reactivex.annotations.NonNull

@AutoValue
abstract class DepartementRaw {

    @NonNull
    abstract fun nom(): String

    @NonNull
    abstract fun code(): String

    //@NonNull
    //abstract fun region(): List<Region>


    companion object {
        /*@NonNull
        fun typeAdapter(gson: Gson): TypeAdapter<DepartementRaw> {
            return AutoValue_DepartementRaw.GsonTypeAdapter(gson)
        }*/
        @NonNull
        fun builder(): Builder {
            return AutoValue_DepartementRaw.Builder()
        }
    }

    @AutoValue.Builder
    interface Builder {

        fun nom(nom: String): Builder

        fun code(code: String): Builder

        //fun region(region: List<Region>): Builder

        fun build(): DepartementRaw
    }
}
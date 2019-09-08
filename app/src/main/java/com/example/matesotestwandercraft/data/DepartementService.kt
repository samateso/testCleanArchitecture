package com.example.matesotestwandercraft.data


import retrofit2.http.GET
import io.reactivex.Single
import retrofit2.http.Path


interface DepartementService {
    @GET("departements")
    fun getDepartement() : Single<List<DepartementRaw>>

    @GET("departements/{code}/communes")
    fun getCommunesDepartement(@Path("code") code : String) : Single<List<DepartementRaw>>
}
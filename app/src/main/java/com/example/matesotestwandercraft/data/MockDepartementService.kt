package com.example.matesotestwandercraft.data

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.InputStreamReader
import java.io.UnsupportedEncodingException
import java.util.ArrayList

class MockDepartementService constructor() : DepartementService {


    var networkCall : NetworkCall = NetworkCall()

    override fun getDepartement(): Single<List<DepartementRaw>> {
        val listType = object : TypeToken<ArrayList<DepartementRaw>>() {
        }.type
        try {
            val callRetrofit = networkCall.getDataApi()
            var request = callRetrofit.create(DepartementService::class.java)
            return request.getDepartement()
        } catch (e: UnsupportedEncodingException) {
            throw IllegalArgumentException("MockDepartementService" + ": Error parsing from file credit_drafts.json")
        }
    }

    override fun getCommunesDepartement(code: String): Single<List<DepartementRaw>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
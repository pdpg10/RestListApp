package com.example.restlistapp.model.net

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://api.ipify.org/"

interface ITestApi {

    @GET("?format=json")
    fun loadPost():
            Single<IpModel>
}

class IpModel(val ip: String)
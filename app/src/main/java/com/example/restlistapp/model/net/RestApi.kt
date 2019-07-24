package com.example.restlistapp.model.net

import com.example.restlistapp.common.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestApiImpl {
    val restApi: IRestApi by lazy {
        val retrofit = initRetrofit()
        retrofit.create(IRestApi::class.java)
    }

    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
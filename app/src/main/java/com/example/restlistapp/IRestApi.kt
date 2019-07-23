package com.example.restlistapp

import retrofit2.Call
import retrofit2.http.GET

interface IRestApi {

    @GET(POSTS)
    fun loadPost(): Call<Array<Post>>
}
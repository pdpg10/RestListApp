package com.example.restlistapp.model.net

import com.example.restlistapp.common.POSTS
import com.example.restlistapp.common.USERS
import com.example.restlistapp.model.Post
import com.example.restlistapp.model.User
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IRestApi {

    @GET(POSTS)
    fun loadPost(): Call<Array<Post>>

    @GET(USERS)
    fun loadUsers(): Call<Array<User>>


    @GET(POSTS)
    fun loadPostRx(): Single<Array<Post>>

    @GET(USERS)
    fun loadUsersRx(): Single<Array<User>>


    @GET(POSTS)
    fun loadPostByUserId(@Query("userId") id: Int)
            : Call<Array<Post>>
}
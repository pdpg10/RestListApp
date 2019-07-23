package com.example.restlistapp

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("userId")
    val serverId: Int,
    val id: Int,
    val title: String,
    val body: String
)
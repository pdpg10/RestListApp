package com.example.restlistapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(
    @SerializedName("userId")
    val serverId: Int,
    val id: Int,
    val title: String,
    val body: String
) : Parcelable
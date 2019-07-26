package com.example.restlistapp.ui.global

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.restlistapp.extension.lazyFast
import com.example.restlistapp.model.Post
import com.example.restlistapp.model.User


//todo fastLazy
//todo optimize sharedVM access
class SharedViewModel : ViewModel() {
    val liveUser by lazyFast { MutableLiveData<User>() }
    val livePost by lazyFast { MutableLiveData<Post>() }
}
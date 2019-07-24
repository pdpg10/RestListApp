package com.example.restlistapp.ui.global

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.restlistapp.model.User


//todo fastLazy
//todo optimize sharedVM access
class SharedViewModel : ViewModel() {
    val liveUser: MutableLiveData<User> by lazy { MutableLiveData<User>() }
}
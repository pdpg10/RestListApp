package com.example.restlistapp.ui.post_item

import android.os.Bundle
import android.util.Log
import com.example.restlistapp.R
import com.example.restlistapp.common.KEY_POST
import com.example.restlistapp.model.Post
import com.example.restlistapp.ui.global.BaseFragment

class PostItemFragment : BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_post_item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val post: Post? = arguments?.getParcelable(KEY_POST)
    }

}
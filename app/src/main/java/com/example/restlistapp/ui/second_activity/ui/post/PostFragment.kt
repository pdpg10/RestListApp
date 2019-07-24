package com.example.restlistapp.ui.second_activity.ui.post

import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restlistapp.R
import com.example.restlistapp.common.USER_ID
import com.example.restlistapp.model.Post
import com.example.restlistapp.model.net.RestApiImpl
import com.example.restlistapp.ui.global.BaseFragment
import kotlinx.android.synthetic.main.fragment_post_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostFragment : BaseFragment() {
    override val layoutRes = R.layout.fragment_post_list
    private var adapter: PostAdapter? = null
    private var call: Call<Array<Post>>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.layoutManager = LinearLayoutManager(this@PostFragment.context)
        this.adapter = PostAdapter()
        list.adapter = adapter
        loadPosts()
    }

    private fun loadPosts() {
        val pref = PreferenceManager.getDefaultSharedPreferences(activity)
        val userId = pref.getInt(USER_ID, 0)
        if (userId == 0) return
        call = RestApiImpl.restApi.loadPostByUserId(userId)
        call?.enqueue(object : Callback<Array<Post>> {
            override fun onFailure(call: Call<Array<Post>>, t: Throwable) {
                Log.e("error", t.message)
            }

            override fun onResponse(
                call: Call<Array<Post>>,
                response: Response<Array<Post>>
            ) {
                val posts = response.body()
                if (posts != null) {
                    adapter?.updateData(posts)
                }
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        call?.cancel()
    }

}

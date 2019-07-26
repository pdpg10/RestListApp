package com.example.restlistapp.ui.second_activity.ui.post

import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restlistapp.R
import com.example.restlistapp.common.USER_ID
import com.example.restlistapp.extension.lazyFast
import com.example.restlistapp.model.Post
import com.example.restlistapp.model.net.RestApiImpl
import com.example.restlistapp.ui.global.BaseFragment
import com.example.restlistapp.ui.global.SharedViewModel
import kotlinx.android.synthetic.main.fragment_post_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostFragment : BaseFragment() {
    // parceable vs serizable
    override val layoutRes = R.layout.fragment_post_list
    private var adapter: PostAdapter? = null
    private var call: Call<Array<Post>>? = null
    private val vm by lazyFast { ViewModelProviders.of(activity!!)[SharedViewModel::class.java] }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.layoutManager = LinearLayoutManager(this@PostFragment.context)
        this.adapter = PostAdapter(this::onPostSelected)
        list.adapter = adapter
        loadPosts()
    }

    private fun onPostSelected(post: Post) {
        vm.livePost.value = post
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

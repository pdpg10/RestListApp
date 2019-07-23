package com.example.restlistapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restlistapp.dummy.DummyContent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostFragment : Fragment() {
    private var api: IRestApi? = null
    private var adapter: MyPostRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNet()
    }

    private fun initNet() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(IRestApi::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (view is RecyclerView) {
            view.layoutManager = LinearLayoutManager(this@PostFragment.context)
            this.adapter = MyPostRecyclerViewAdapter()
            view.adapter = adapter
        }
        loadPosts()

    }

    private fun loadPosts() {
        val posts = api?.loadPost()?.enqueue(object : Callback<Array<Post>> {
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

}

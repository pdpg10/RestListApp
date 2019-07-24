package com.example.restlistapp.ui.user_list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.restlistapp.R
import com.example.restlistapp.model.User
import com.example.restlistapp.model.net.RestApiImpl
import com.example.restlistapp.ui.global.BaseFragment
import com.example.restlistapp.ui.global.SharedViewModel
import kotlinx.android.synthetic.main.fragment_post_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    override val layoutRes: Int = R.layout.fragment_user_list
    private var adapter: UserAdapter? = null
    private val listCalls: MutableList<Call<*>> = mutableListOf()
    private val sharedVM by lazy { ViewModelProviders.of(activity!!)[SharedViewModel::class.java] }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.layoutManager = LinearLayoutManager(this@UserFragment.context)
        this.adapter = UserAdapter(context!!, this::onUserSelected)
        list.adapter = adapter
        swipe_refresh.isRefreshing = true
        swipe_refresh.setOnRefreshListener(this)
        loadUsers()
    }

    private fun onUserSelected(user: User) {
        sharedVM.liveUser.value = user
    }

    private fun loadUsers() {
        val call = RestApiImpl.restApi.loadUsers()
        call.enqueue(object : Callback<Array<User>> {
            override fun onFailure(call: Call<Array<User>>, t: Throwable) {
                //todo show snackbar
                swipe_refresh.isRefreshing = false
            }

            override fun onResponse(call: Call<Array<User>>, response: Response<Array<User>>) {
                val users = response.body()
                if (users != null) {
                    adapter?.updateData(users)
                }
                swipe_refresh.isRefreshing = false
            }

        })
        listCalls += call
    }

    override fun onRefresh() {
        loadUsers()
        swipe_refresh.isRefreshing = false
    }

    override fun onDestroy() {
        super.onDestroy()
        for (it in listCalls) {
            it.cancel()
        }
    }

}

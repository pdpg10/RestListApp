package com.example.restlistapp.ui.user_list

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.restlistapp.R
import com.example.restlistapp.model.Post
import com.example.restlistapp.model.User
import com.example.restlistapp.model.net.RestApiImpl
import com.example.restlistapp.ui.global.BaseFragment
import com.example.restlistapp.ui.global.SharedViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_post_list.*
import retrofit2.Call

class UserFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    override val layoutRes: Int = R.layout.fragment_user_list
    private var adapter: UserAdapter? = null
    private val listCalls: MutableList<Call<*>> = mutableListOf()
    private val sharedVM by lazy { ViewModelProviders.of(activity!!)[SharedViewModel::class.java] }
    private var d: Disposable? = null
    private val cd = CompositeDisposable()

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
//        val d = RestApiImpl.restApi.loadUsersRx()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .doOnEvent { _, _ -> swipe_refresh.isRefreshing = false }
//            .subscribe(
//                { adapter?.updateData(it) },
//                { Log.e("loadUsers", it.message) })
//        cd.add(d)


//        RestApiImpl.restApi.loadUsers().enqueue(object : Callback<Array<User>> {
//            override fun onFailure(call: Call<Array<User>>, t: Throwable) {
//
//
//            }
//
//            override fun onResponse(call: Call<Array<User>>, response: Response<Array<User>>) {
//
//                RestApiImpl.restApi.loadPost().enqueue(object : Callback<Array<Post>> {
//                    override fun onFailure(call: Call<Array<Post>>, t: Throwable) {
//                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                    }
//
//                    override fun onResponse(
//                        call: Call<Array<Post>>,
//                        response: Response<Array<Post>>
//                    ) {
//                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                    }
//
//                })
//            }
//        })

        val api = RestApiImpl.restApi

        val d1 = Single
            .zip<Array<Post>, Array<User>, Pair<Array<Post>, Array<User>>>(
                api.loadPostRx(),
                api.loadUsersRx(),
                BiFunction { posts, users -> Pair(posts, users) }
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ adapter?.updateData(it.second) }, { Log.e("loadUsers", it.message) })
    }

    override fun onRefresh() {
        loadUsers()
        swipe_refresh.isRefreshing = false
    }

    override fun onDestroy() {
        super.onDestroy()
        //todo compositediposable clear vs dispose
        cd.clear()

    }

}

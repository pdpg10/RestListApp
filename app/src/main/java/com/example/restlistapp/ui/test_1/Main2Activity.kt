package com.example.restlistapp.ui.test_1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.restlistapp.R
import com.example.restlistapp.model.net.IpModel
import com.example.restlistapp.model.net.TestApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.content_main2.*
import java.util.concurrent.TimeUnit

class Main2Activity : AppCompatActivity() {
    private val cd = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            loadFromNet()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun loadFromNet() {
        val api = TestApi.restApi
        val observableCall: Observable<IpModel> = api.loadPost().toObservable()

        val d = Observable.interval(3, TimeUnit.SECONDS)//Observable<Long>
            .flatMap { observableCall }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                tv.text = "${it.ip} \n ${System.currentTimeMillis()}"
            }, {})
        cd.add(d)
    }

    private fun networkCall() {

    }

    override fun onDestroy() {
        super.onDestroy()
        cd.clear()
    }
}

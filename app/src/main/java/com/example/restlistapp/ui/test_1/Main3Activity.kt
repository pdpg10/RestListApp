package com.example.restlistapp.ui.test_1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.restlistapp.R
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import kotlinx.android.synthetic.main.activity_main3.*
import kotlinx.android.synthetic.main.content_main3.*

class Main3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        setSupportActionBar(toolbar)
        Observable.combineLatest(
            et_login
                .textChanges()
                .skip(1)
                .doOnNext { if (it.isNotEmpty()) ti_login.error = "some error" },
            et_pass.textChanges()
                .skip(1)
                .doOnNext { if (it.isNotEmpty()) ti_login.error = "some error" }, ,
            BiFunction { t1, t2 -> button.isEnabled = t1.isNotEmpty() && t2.isNotEmpty() }
        ).subscribe()
    }

}

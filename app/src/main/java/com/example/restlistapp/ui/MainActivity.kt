package com.example.restlistapp.ui

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.restlistapp.R
import com.example.restlistapp.common.USER_ID
import com.example.restlistapp.model.User
import com.example.restlistapp.ui.global.SharedViewModel
import com.example.restlistapp.ui.second_activity.SecondActivity

class MainActivity : AppCompatActivity() {

    //todo allowBackUp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedVM = ViewModelProviders.of(this)[SharedViewModel::class.java]
        sharedVM.liveUser.observe(this, Observer {
            startNextActivity(it)
        })
    }

    private fun startNextActivity(it: User?) {
        if (it == null) return
        val intent = Intent(this, SecondActivity::class.java)
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit().putInt(USER_ID, it.id).apply()
        startActivity(intent)
    }
}

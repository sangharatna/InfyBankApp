package com.app.infybankapp.splash.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.app.infybankapp.R
import com.app.infybankapp.login.view.LoginActivity


class SplashActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var handler = Handler()
        handler.postDelayed({

            startActivity(Intent(this@SplashActivity,
                LoginActivity::class.java))
            finish()
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        },4000)

    }

}

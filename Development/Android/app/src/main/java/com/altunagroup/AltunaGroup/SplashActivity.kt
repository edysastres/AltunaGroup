package com.altunagroup.AltunaGroup

import android.content.Intent
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME : Long = 2000

    lateinit var preferencesHelper: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)

        preferencesHelper = PreferencesHelper(this)

        Handler().postDelayed(Runnable {

            var intent: Intent
            if(preferencesHelper.loggedIn) {
                intent = Intent(this, EventsActivity::class.java)
                startActivity(intent)
            } else {
                intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            finish()
        }, SPLASH_TIME)
    }
}

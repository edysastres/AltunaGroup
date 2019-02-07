package com.altunagroup.AltunaGroup

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var preferencesHelper: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        preferencesHelper = PreferencesHelper(this)

        btnLogin.setOnClickListener {
            Snackbar.make(it, "Bienvenido! Credenciales correctas.", Snackbar.LENGTH_INDEFINITE)
                .setAction("OK", View.OnClickListener {

                    preferencesHelper.loggedIn = true

                    var intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                })
                .show()
        }

        btnForgotPassword.setOnClickListener {
            var intent = Intent(this, PasswordActivity::class.java)
            startActivity(intent)
        }
    }
}

package com.altunagroup.AltunaGroup

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    lateinit var preferencesHelper: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Administrador de preferencias
        preferencesHelper = PreferencesHelper(this)

        btnLogin.setOnClickListener {
            //Lanza la solicitud para validar si es posible iniciar sesión o no
            RequestHelper(this).tryLogin(LoginData(txtLoginUser.text.toString(), txtLoginPass.text.toString()))
        }

        btnForgotPassword.setOnClickListener {
            //Re-dirige a la actividad de contraseña olvidada
            var intent = Intent(this, PasswordActivity::class.java)
            startActivity(intent)
        }
    }
}

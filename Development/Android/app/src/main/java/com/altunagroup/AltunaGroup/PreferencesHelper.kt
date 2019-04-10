package com.altunagroup.AltunaGroup

import android.content.Context
import android.preference.PreferenceManager

//Clase administradora de preferencias
class PreferencesHelper(context: Context){
    private val LOGGED_IN = "data.source.prefs.LOGGED_IN" //Almacena si ya tiene sesión iniciada o no
    private val USER_ID = "data.source.prefs.USER_ID" //Almacena el ID de usuario obtenido al iniciar sesión
    private val USER_NAME = "data.source.prefs.USER_NAME" //Almacena el nombre de usuario dado al iniciar sesión
    private val CUSTOMER_ID = "data.source.prefs.CUSTOMER_ID" //Almacena el Customer ID
    private val CUSTOMER_NAME = "data.source.prefs.CUSTOMER_NAME" //Almacena el Customer Name
    private val SESSION_TOKEN = "data.source.prefs.SESSION_TOKEN" //Almacena el Token de autorización para realizar las solicitudes
    private val PASSWORD = "data.source.prefs.PASS" //Almacena la contraseña del usuario
    private val DB_ID = "data.source.prefs.DB_ID" //Almacena el ID de la base de datos a la que se conecta el usuario

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    //SESSION INFO
    var loggedIn = preferences.getBoolean(LOGGED_IN, false)
        set(value) = preferences.edit().putBoolean(LOGGED_IN, value).apply()

    //USER INFO
    var userID = preferences.getString(USER_ID, "-")
        set(value) = preferences.edit().putString(USER_ID, value).apply()
    var userName = preferences.getString(USER_NAME, "")
        set(value) = preferences.edit().putString(USER_NAME, value).apply()

    //Customer Info
    var customerID = preferences.getInt(CUSTOMER_ID, -1)
        set(value) = preferences.edit().putInt(CUSTOMER_ID, value).apply()

    var customerName = preferences.getString(CUSTOMER_NAME, "")
        set(value) = preferences.edit().putString(CUSTOMER_NAME, value).apply()

    //Autorización
    var token = preferences.getString(SESSION_TOKEN, "")
        set(value) = preferences.edit().putString(SESSION_TOKEN, value).apply()

    var pass = preferences.getString(PASSWORD, "")
        set(value) = preferences.edit().putString(PASSWORD, value).apply()

    var dbId = preferences.getString(DB_ID, "")
        set(value) = preferences.edit().putString(DB_ID, value).apply()
}
package com.altunagroup.AltunaGroup

import android.content.Context
import android.preference.PreferenceManager

class PreferencesHelper(context: Context){
    private val LOGGED_IN = "data.source.prefs.LOGGED_IN"
    private val USER_ID = "data.source.prefs.USER_ID"
    private val USER_NAME = "data.source.prefs.USER_NAME"
    private val CUSTOMER_ID = "data.source.prefs.CUSTOMER_ID"
    private val CUSTOMER_NAME = "data.source.prefs.CUSTOMER_NAME"

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    //SESSION INFO
    var loggedIn = preferences.getBoolean(LOGGED_IN, false)
        set(value) = preferences.edit().putBoolean(LOGGED_IN, value).apply()

    //USER INFO
    var userID = preferences.getInt(USER_ID, -1)
        set(value) = preferences.edit().putInt(USER_ID, value).apply()
    var userName = preferences.getString(USER_NAME, "")
        set(value) = preferences.edit().putString(USER_NAME, value).apply()

    //Customer Info
    var customerID = preferences.getInt(CUSTOMER_ID, -1)
        set(value) = preferences.edit().putInt(CUSTOMER_ID, value).apply()

    var customerName = preferences.getString(CUSTOMER_NAME, "")
        set(value) = preferences.edit().putString(CUSTOMER_NAME, value).apply()
}
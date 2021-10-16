package com.proceedto15.wb.utilities

import android.content.Context
import android.content.SharedPreferences

class Preferences(context: Context) {

    val PREFERENCE_NAME = "sharedpreferences"
    val FIRST_TIME = "shared_first_time"
    val pref: SharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, 0)

    var firstTime: String
        get() = pref.getString(FIRST_TIME, "")!!
        set(value) = pref.edit().putString(FIRST_TIME, value).apply()
}
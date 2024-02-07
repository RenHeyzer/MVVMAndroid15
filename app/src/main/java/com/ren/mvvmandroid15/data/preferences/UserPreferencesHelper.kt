package com.ren.mvvmandroid15.data.preferences

import android.content.Context
import android.content.SharedPreferences
import com.ren.mvvmandroid15.R

object UserPreferencesHelper {

    private const val NAME_PREFERENCE_KEY = "name"
    private const val AGE_PREFERENCE_KEY = "age"
    private const val EMAIL_PREFERENCE_KEY = "email"
    private const val PASSWORD_PREFERENCE_KEY = "password"

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(
            context.getString(R.string.shared_preferences_name),
            Context.MODE_PRIVATE
        )
    }

    var name: String?
        get() = sharedPreferences.getString(NAME_PREFERENCE_KEY, "")
        set(value) = sharedPreferences.edit().putString(NAME_PREFERENCE_KEY, value).apply()

    var age: Int
        get() = sharedPreferences.getInt(AGE_PREFERENCE_KEY, 0)
        set(value) = sharedPreferences.edit().putInt(AGE_PREFERENCE_KEY, value).apply()
    var email: String?
        get() = sharedPreferences.getString(EMAIL_PREFERENCE_KEY, "")
        set(value) = sharedPreferences.edit().putString(EMAIL_PREFERENCE_KEY, value).apply()
    var password: String?
        get() = sharedPreferences.getString(PASSWORD_PREFERENCE_KEY, "")
        set(value) = sharedPreferences.edit().putString(PASSWORD_PREFERENCE_KEY, value).apply()
}
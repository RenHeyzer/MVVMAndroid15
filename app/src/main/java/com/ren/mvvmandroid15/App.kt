package com.ren.mvvmandroid15

import android.app.Application
import com.ren.mvvmandroid15.data.preferences.UserPreferencesHelper

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        UserPreferencesHelper.init(this)
    }
}
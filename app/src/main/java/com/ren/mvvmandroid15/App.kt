package com.ren.mvvmandroid15

import android.app.Application
import com.ren.mvvmandroid15.data.preferences.PreferencesHelper

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        PreferencesHelper.init(this)
    }
}
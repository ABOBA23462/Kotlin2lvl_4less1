package com.example.kotlin2lvl_2less

import android.app.Application
import com.example.kotlin2lvl_2less.utils.PreferenceHelper

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initPref()
    }

    private fun initPref() {
        val preferenceHelper = PreferenceHelper()
        preferenceHelper.unit(this)
    }
}
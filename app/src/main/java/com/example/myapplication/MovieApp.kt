package com.example.myapplication

import android.app.Application
import com.example.myapplication.utils.DarkModeHelper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieApp: Application() {

    override fun onCreate() {
        super.onCreate()
        DarkModeHelper.getInstance(this)
    }
}
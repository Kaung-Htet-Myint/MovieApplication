package com.example.myapplication.utils

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit

class DarkModeHelper private constructor(
    private val context: Context
) {

    private val sharedPreferences = context.getSharedPreferences("dark", Context.MODE_PRIVATE)

    fun isDark() = sharedPreferences.getBoolean("is_dark", false)

    init {
        val isDark = isDark()
        if (isDark) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }


    fun toggleDark() {
        val isDark = sharedPreferences.getBoolean("is_dark", false)
        if (isDark) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        sharedPreferences.edit { putBoolean("is_dark", !isDark) }
    }

    companion object {

        private var instance: DarkModeHelper? = null

        fun getInstance(context: Context) = instance ?: DarkModeHelper(context)
    }
}
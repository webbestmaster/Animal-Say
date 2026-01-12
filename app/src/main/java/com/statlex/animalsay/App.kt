package com.statlex.animalsay

import android.app.Application
import android.content.Context
import com.statlex.animalsay.util.initAppLanguage

class App : Application() {
/*
    override fun attachBaseContext(newBase: Context) {
        val localizedContext = initAppLanguage(newBase)
        super.attachBaseContext(localizedContext)
    }
*/

    override fun onCreate() {
        super.onCreate()
    }
}
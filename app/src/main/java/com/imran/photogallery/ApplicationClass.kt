package com.imran.photogallery

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationClass: Application() {
    companion object{
        var appContext: Context? = null
    }
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}
package com.qcoom.photogallery

import android.app.Application
import android.content.Context

class ApplicationClass: Application() {
    companion object{
        var appContext: Context? = null
    }
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}
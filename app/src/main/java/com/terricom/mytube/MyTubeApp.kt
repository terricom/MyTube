package com.terricom.mytube

import android.app.Application
import android.content.Context

class MyTubeApp : Application() {

    companion object {
        var instance : MyTubeApp? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
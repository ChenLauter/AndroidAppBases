package com.lauter.androidappbases.common

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class CommonApp: Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}
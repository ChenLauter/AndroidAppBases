package com.lauter.androidappbases.common

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.lauter.androidappbases.apt.api.AppLifecycleManager
import com.tencent.mmkv.MMKV

class CommonApp: Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        MMKV.initialize(this)
        AppLifecycleManager.onCreate(this)
    }
}
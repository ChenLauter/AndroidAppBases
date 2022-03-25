package com.lauter.androidappbases.module.home

import android.content.Context
import android.util.Log
import com.lauter.androidappbases.apt.annotation.AppLifecycle
import com.lauter.androidappbases.apt.api.IAppLifecycleCallback

@AppLifecycle
class HomeAppLifecycleCallback : IAppLifecycleCallback {

    override fun onCreate(context: Context) {
        Log.d("bitch", "HomeAppLifecycleCallback on create")
    }

    override fun getPriority(): Int = 1
}
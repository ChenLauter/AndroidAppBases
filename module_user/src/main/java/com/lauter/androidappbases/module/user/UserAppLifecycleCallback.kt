package com.lauter.androidappbases.module.user

import android.content.Context
import android.util.Log
import com.lauter.androidappbases.apt.annotation.AppLifecycle
import com.lauter.androidappbases.apt.api.IAppLifecycleCallback

@AppLifecycle
class UserAppLifecycleCallback : IAppLifecycleCallback{

    override fun onCreate(context: Context) {
        Log.d("bitch", "UserAppLifecycleCallback on create")
    }

    override fun getPriority(): Int = 2

}
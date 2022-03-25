package com.lauter.androidappbases.apt.api

import android.content.Context

interface IAppLifecycleCallback {

    fun onCreate(context: Context)
    fun getPriority(): Int = 0
}
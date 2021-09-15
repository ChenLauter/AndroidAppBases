package com.lauter.androidappbases.common

import android.app.Application
import android.content.Context
import com.tencent.mmkv.MMKV

open class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        baseApplication = this
        MMKV.initialize(this)
    }

    companion object {
        private lateinit var baseApplication: BaseApp

        fun getContext(): Context {
            return baseApplication
        }
    }
}
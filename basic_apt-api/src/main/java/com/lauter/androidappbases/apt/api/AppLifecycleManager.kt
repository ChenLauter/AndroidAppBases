package com.lauter.androidappbases.apt.api

import android.content.Context


object AppLifecycleManager {

    private var callbacks: MutableList<IAppLifecycleCallback>? = null

    fun registerAppLifecycle(name: String) {
        try {
            if (callbacks == null) {
                callbacks = mutableListOf()
            }
            val instance = Class.forName(name).getConstructor().newInstance()
            if (instance is IAppLifecycleCallback && !callbacks!!.contains(instance)) {
                callbacks!!.add(instance)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun onCreate(context: Context) {
        callbacks?.run {
            sortBy { it.getPriority() }
            for (item in this) {
                item.onCreate(context)
            }
        }
    }

}
package com.lauter.androidappbases.common.utils

import android.util.Log

object LogUtil {

    private const val tag = "mb-tag"

    fun d(msg: String) {
        this.println(Log.DEBUG,msg)
    }

    fun e(msg: String) {
        this.println(Log.ERROR,msg)
    }

    fun d(msg: Any) {
        this.println(Log.DEBUG,msg.toString())
    }

    fun e(msg: Any) {
        this.println(Log.ERROR,msg.toString())
    }

    private fun println(priority: Int, msg: String) {
        val stack = Throwable().stackTrace[2]
        val className = stack.className
        val methodName = stack.methodName
        Log.println(priority, tag,"┌─ $className ==> $methodName")
        Log.println(priority, tag,"└─ $msg")
    }
}
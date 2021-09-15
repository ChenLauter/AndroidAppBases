package com.lauter.androidappbases.common.utils

import com.tencent.mmkv.MMKV

object SPUtil {

    fun putInt(key:String, value:Int) {
        MMKV.defaultMMKV()?.putInt(key, value)
    }

    fun getInt(key:String,def:Int): Int {
        val value = MMKV.defaultMMKV()?.getInt(key,def)
        return value?:def
    }

    fun getInt(key:String): Int {
        val value = MMKV.defaultMMKV()?.getInt(key,0)
        return value?:0
    }

    fun putString(key:String, value:String) {
        MMKV.defaultMMKV()?.putString(key, value)
    }

    fun getString(key:String, def:String): String {
        val value = MMKV.defaultMMKV()?.getString(key, def)
        return value?:def
    }

    fun getString(key:String): String {
        val value = MMKV.defaultMMKV()?.getString(key, "")
        return value?:""
    }

    fun putBoolean(key: String,value: Boolean) {
        MMKV.defaultMMKV()?.putBoolean(key, value)
    }

    fun getBoolean(key: String,def:Boolean): Boolean {
        val value = MMKV.defaultMMKV()?.getBoolean(key,def)
        return value?:def
    }

    fun getBoolean(key: String): Boolean {
        val value = MMKV.defaultMMKV()?.getBoolean(key,false)
        return value?:false
    }

}
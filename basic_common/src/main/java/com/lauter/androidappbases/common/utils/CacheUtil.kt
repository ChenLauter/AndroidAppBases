package com.lauter.androidappbases.common.utils

import com.tencent.mmkv.MMKV

object CacheUtil {

    fun getCache(key: String, def: String=""): String {
        return MMKV.mmkvWithID("cache")?.getString(key,def) ?: def
    }

    fun putCache(key: String, value: String) {
        MMKV.mmkvWithID("cache")?.putString(key, value)
    }
}
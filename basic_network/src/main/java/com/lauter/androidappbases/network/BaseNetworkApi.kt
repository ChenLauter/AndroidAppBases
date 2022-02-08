package com.lauter.androidappbases.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit

abstract class BaseNetworkApi {

    /**
     * 用于存储ApiService
     */
    private val map = mutableMapOf<Class<*>, Any>()
    /**
     * 只初始化一次
     */


    open fun <T : Any> getApiService(apiClass: Class<T>): T {
        return getService(apiClass)
    }

    private fun <T : Any> getService(apiClass: Class<T>): T{
        //重入锁单例避免多线程安全问题
        return if (map[apiClass] == null) {
            synchronized(this::class.java) {
                val t = retrofit.create(apiClass)
                if (map[apiClass] == null) {
                    map[apiClass] = t
                }
                t
            }
        } else {
            map[apiClass] as T
        }
    }

    private val retrofit by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        val client = setOkHttpBuilder().build()
        val builder = Retrofit.Builder()
        setRetrofitBuilder(builder)
            .client(client)
            .build()
    }

    abstract fun setOkHttpBuilder(): OkHttpClient.Builder
    abstract fun setRetrofitBuilder(builder: Retrofit.Builder): Retrofit.Builder
}
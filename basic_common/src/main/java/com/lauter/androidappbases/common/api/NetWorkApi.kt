package com.lauter.androidappbases.common.api

import com.lauter.androidappbases.network.BaseNetworkApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetWorkApi : BaseNetworkApi() {

    companion object {
        val INSTANCE by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { NetWorkApi() }
    }

    private val debugUrl = "https://www.wanandroid.com"
    private val releaseUrl = "https://www.wanandroid.com"
    private val isDebug = true

    override fun setOkHttpBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }

    override fun setRetrofitBuilder(builder: Retrofit.Builder): Retrofit.Builder {
        builder
            .baseUrl(if (isDebug) debugUrl else releaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
        return builder
    }
}
package com.lauter.androidappbases.http


import android.os.Build
import com.lauter.androidappbases.common.BaseApp.Companion.getContext
import com.lauter.androidappbases.common.constant.CommonConst.NetWork.BASE_URL
import com.lauter.androidappbases.common.constant.CommonConst.NetWork.DEFAULT_TIMEOUT
import com.lauter.androidappbases.common.utils.DeviceUtil
import okhttp3.*
import retrofit2.Retrofit
import java.io.File
import java.util.concurrent.TimeUnit

object RetrofitFactory {
    //缓存100Mb
    private val okHttpClientBuilder: OkHttpClient.Builder
        get() {
            return OkHttpClient.Builder()
                .readTimeout(
                    DEFAULT_TIMEOUT,
                    TimeUnit.MILLISECONDS
                )
                .connectTimeout(
                    DEFAULT_TIMEOUT,
                    TimeUnit.MILLISECONDS
                )
                .cache(getCache())
//                .proxy(if (BuildConfig.DEBUG) null else Proxy.NO_PROXY)


        }

    fun factory(): Retrofit {
        val okHttpClient = okHttpClientBuilder.build()
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    /**
     * 获取日志拦截器
     */
//    private fun getLogInterceptor():Interceptor{
//        //http log 拦截器
//        return HttpLoggingInterceptor("OkHttp").apply {
//                setPrintLevel(HttpLoggingInterceptor.Level.BODY)
//                setColorLevel(Level.INFO)
//            }
//    }

    /**
     * 获取cookie持久化
     */
//    private fun getCookie():ClearableCookieJar{
//        return PersistentCookieJar(
//            SetCookieCache(),
//            SharedPrefsCookiePersistor(getContext())
//        )
//    }


    /**
     * 获取缓存方式
     */
    private fun getCache():Cache{
        //缓存100Mb
        return Cache( File(getContext().cacheDir, "cache")
            , 1024 * 1024 * 100)
    }

    private val chParam = HashMap<String, Any>().apply {
        put("device_id", DeviceUtil.getAndroidId())
        put("os_v",Build.VERSION.SDK_INT)
        put("os_vv",Build.VERSION.RELEASE)
        put("app_vn", DeviceUtil.getAppVersionName())
        put("app_vc", DeviceUtil.getAppVersionCode())
        put("app_name", DeviceUtil.getAppName())
        put("brand",Build.BRAND)
        put("model",Build.MODEL)
        put("imei", DeviceUtil.getImei()?:"")
    }
}
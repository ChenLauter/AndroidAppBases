package com.lauter.androidappbases.common.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.provider.Settings
import android.telephony.TelephonyManager
import android.text.TextUtils
import android.util.DisplayMetrics
import android.view.WindowManager
import com.lauter.androidappbases.common.BaseApp

object DeviceUtil {

    @SuppressLint("HardwareIds")
    @JvmStatic
    fun getAndroidId(): String {
        return Settings.Secure.getString(BaseApp.getContext().contentResolver, Settings.Secure.ANDROID_ID)
//        return "50b4b713d0792e56"
    }

    @SuppressLint("MissingPermission")
    fun getImei(): String? {
        return try {
            val manager = BaseApp.getContext().getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            manager.deviceId
        } catch (e: Exception) {
            null
        }

    }

    fun isProxy(): Boolean {
        val address = System.getProperty("http.proxyHost")
        val portContent = System.getProperty("http.proxyPort")
        val port = portContent?.toInt()?:-1
        return (!TextUtils.isEmpty(address) && port != -1)
    }

    @JvmStatic
    fun getScreenWidth(context: Context): Int {
        return context.resources.displayMetrics.widthPixels
    }

    @JvmStatic
    fun getScreenHeight(context: Context): Int {
//        return context.resources.displayMetrics.heightPixels
        val metrics = DisplayMetrics()
        (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getRealMetrics(metrics)
        return metrics.heightPixels
    }

    fun captureWithStatusBar(activity: Activity): Bitmap {
        val view = activity.window.decorView
        view.isDrawingCacheEnabled = true
        view.buildDrawingCache()
        val bmp = view.drawingCache
        val width = getScreenWidth(activity)
        val height = getScreenHeight(activity)
        val ret = Bitmap.createBitmap(bmp, 0, 0, width, height)
        view.destroyDrawingCache()
        return ret
    }

    @JvmStatic
    fun getAppVersionName() : String {
        return BaseApp.getContext().let {
            it.packageManager.getPackageInfo(it.packageName,0).versionName
        } ?: ""
    }

    @JvmStatic
    fun getAppVersionCode() : Int {
        return BaseApp.getContext().let {
            it.packageManager.getPackageInfo(it.packageName,0).versionCode
        }
    }

    @JvmStatic
    fun getAppName(): String {
        return with(BaseApp.getContext()) {
            val info = packageManager.getApplicationInfo(packageName,PackageManager.GET_META_DATA)
            packageManager.getApplicationLabel(info).toString()
        }
    }

    @JvmStatic
    fun isWifi(): Boolean {
        var result = false
        val manager = BaseApp.getContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        manager.allNetworks.forEach {
            manager.getNetworkInfo(it)?.apply {
                if (type == ConnectivityManager.TYPE_WIFI) {
                    result = result or isConnected
                }
            }
        }
        return result
    }
}
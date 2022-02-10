package com.lauter.androidappbases.common

import android.net.Uri

object CommonDeeplink {

    fun getWebUri(url: String, title: String): Uri {
        val string = CommonApp.context.getString(R.string.deeplink_web).run {
            replace("{loadUrl}",url)
                .replace("{title}",title)
        }
        return Uri.parse(string)
    }
    
    val loginUri: Uri by lazy { Uri.parse(CommonApp.context.getString(R.string.deeplink_login)) }
}
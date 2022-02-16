package com.lauter.androidappbases.module.home.deeplink

import android.net.Uri
import com.lauter.androidappbases.common.CommonApp
import com.lauter.androidappbases.module.home.R

object HomeDeeplink {

    val searchUri: Uri by lazy { Uri.parse(CommonApp.context.getString(R.string.deeplink_search)) }

    fun getSearchResultUri(key: String): Uri {
        val string = CommonApp.context.getString(R.string.deeplink_search_result).replace("{key}", key)
        return Uri.parse(string)
    }
}
package com.lauter.androidappbases.module.home.deeplink

import android.net.Uri
import com.lauter.androidappbases.common.CommonApp
import com.lauter.androidappbases.module.home.R

object HomeDeeplink {

    val searchUri: Uri by lazy { Uri.parse(CommonApp.context.getString(R.string.deeplink_search)) }

    fun getSearchResultUri(): Uri {
        val string = CommonApp.context.getString(R.string.deeplink_search_result)
        return Uri.parse(string)
    }
}
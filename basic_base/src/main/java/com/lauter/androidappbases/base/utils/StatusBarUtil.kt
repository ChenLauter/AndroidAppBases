package com.lauter.androidappbases.base.utils

import android.content.res.Resources

object StatusBarUtil {

    fun getStatusBarHeight(): Int {
        val resourceId =
            Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android")
        return Resources.getSystem().getDimensionPixelSize(resourceId)
    }
}
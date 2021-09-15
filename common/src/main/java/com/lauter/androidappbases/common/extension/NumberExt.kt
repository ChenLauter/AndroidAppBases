package com.lauter.androidappbases.common.extension

import android.content.Context
import com.lauter.androidappbases.common.BaseApp

fun dip2px(dpValue: Float): Int {
    val scale = BaseApp.getContext().resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}

fun px2dip(dpValue: Float): Int {
    val scale: Float = BaseApp.getContext().resources.displayMetrics.density
    return (dpValue / scale + 0.5f).toInt()
}
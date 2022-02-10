package com.lauter.androidappbases.base.extension

import android.content.Context
import android.text.TextUtils
import android.util.TypedValue
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.toast(content: String, duration: Int = Toast.LENGTH_SHORT) {
    if (TextUtils.isEmpty(content)) return
    Toast.makeText(this, content, duration).show()
}

fun Context.toast(@StringRes id: Int, duration: Int = Toast.LENGTH_SHORT) {
    toast(getString(id), duration)
}

fun Context.longToast(content: String) {
    toast(content, Toast.LENGTH_LONG)
}

fun Context.longToast(@StringRes id: Int) {
    toast(getString(id), Toast.LENGTH_LONG)
}

fun Context.dp2px(dp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)
}
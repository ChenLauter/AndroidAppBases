package com.lauter.androidappbases.base.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

object CommonUtil {

    fun hideSofiInput(activity: Activity) {
        val manager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(activity.window.decorView.windowToken,0)
    }
}
package com.lauter.androidappbases.base.utils

import android.content.res.Resources
import android.view.View

object StatusBarUtil {

    fun getStatusBarHeight(): Int {
        val resourceId =
            Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android")
        return Resources.getSystem().getDimensionPixelSize(resourceId)
    }

    fun setFakeStatusBarViewHeight(fakeViewId: String,rootView: View) {
        rootView.run {
            val id = resources.getIdentifier(fakeViewId, "id", this.context.packageName)
            findViewById<View>(id)?.layoutParams?.height = getStatusBarHeight()
        }
    }
}
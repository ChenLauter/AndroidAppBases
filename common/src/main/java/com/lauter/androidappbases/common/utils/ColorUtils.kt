package com.lauter.androidappbases.common.utils

import android.graphics.Color
import android.text.TextUtils
import androidx.core.content.ContextCompat
import com.lauter.androidappbases.common.BaseApp
import com.lauter.androidappbases.common.BaseApp.Companion.getContext
import java.lang.Exception

object ColorUtils {

    fun parseColor(colorStr: String, defaultColor: Int): Int {
        return if (TextUtils.isEmpty(colorStr)) {
            defaultColor
        } else try {
            var color = colorStr
            if (!color.startsWith("#")) {
                color = "#$colorStr"
            }
            Color.parseColor(color)
        } catch (e: Exception) {
            defaultColor
        }
    }

    fun parseColor(colorStr: String): Int {
        return parseColor(colorStr,0)
    }

    fun parseColor(color: Int): Int {
        return ContextCompat.getColor(getContext(), color)
    }

}
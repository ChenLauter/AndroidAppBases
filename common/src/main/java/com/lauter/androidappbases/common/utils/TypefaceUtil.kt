package com.lauter.androidappbases.common.utils

import android.graphics.Typeface
import com.lauter.androidappbases.common.BaseApp
import java.lang.ref.WeakReference

object TypefaceUtil {

    private val typefaceMap = HashMap<String, WeakReference<Typeface>>()

    fun getTypeface(font: String): Typeface? {
        return getFontPath(font)?.let { path ->
            typefaceMap[font]?.get()?:let {
                Typeface.createFromAsset(BaseApp.getContext().assets,path).apply {
                    typefaceMap[font] = WeakReference(this)
                }
            }
        }
    }

    private fun getFontPath(font: String): String? {
        return when (font) {
            else -> null
        }
    }
}
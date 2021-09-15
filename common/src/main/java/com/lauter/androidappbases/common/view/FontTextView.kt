package com.lauter.androidappbases.common.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.lauter.androidappbases.common.R
import com.lauter.androidappbases.common.utils.TypefaceUtil

/**
 * 字体TextView，配合TypefaceUtil使用
 */
class FontTextView : AppCompatTextView {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs,0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val obtain = context.obtainStyledAttributes(attrs, R.styleable.FontTextView)
        val font = obtain.getString(R.styleable.FontTextView_font_type)
        font?.let { type ->
            TypefaceUtil.getTypeface(type)?.let {
                typeface = it
            }
        }
        obtain.recycle()
    }
}
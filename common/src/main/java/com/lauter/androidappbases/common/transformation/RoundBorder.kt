package com.lauter.androidappbases.common.transformation

import android.graphics.*
import android.view.animation.TranslateAnimation
import androidx.annotation.ColorInt
import androidx.core.graphics.scale
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.TransformationUtils
import com.lauter.androidappbases.common.utils.ColorUtils
import com.lauter.androidappbases.common.utils.LogUtil
import java.security.MessageDigest
import kotlin.math.min

/**
 * glide圆角边框
 */
data class RoundBorder(private val topLeftRadius: Float,
                       private val topRightRadius: Float,
                       private val bottomRightRadius: Float,
                       private val bottomLeftRadius: Float,
                       private val borderSize: Float,
                       @ColorInt private val borderColor: Int) : BitmapTransformation() {

    constructor(radius: Float, borderSize: Float, @ColorInt borderColor: Int) :
            this(radius,radius,radius,radius,borderSize, borderColor)

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update("${this::class.java.name}$borderSize)".toByteArray(CHARSET))
    }

    override fun transform(
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap {
        val width = min(outWidth, toTransform.width)
        val height = min(outHeight, toTransform.height)
        val bitmap = pool.get(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        if (borderSize > 0) {
            val borderPaint = Paint().apply {
                isAntiAlias = true
                style = Paint.Style.STROKE
                color = borderColor
                strokeWidth = borderSize*1.2f
            }

            val borderPath = Path().apply {
                val half = borderSize*1.2f/2
                addRoundRect(RectF(half,half,width-half,height-half), floatArrayOf(
                    topLeftRadius,
                    topLeftRadius,
                    topRightRadius,
                    topRightRadius,
                    bottomRightRadius,
                    bottomRightRadius,
                    bottomLeftRadius,
                    bottomLeftRadius
                ),Path.Direction.CW)
            }
            canvas.drawPath(borderPath,borderPaint)
        }
        val scaleBitmap = TransformationUtils.roundedCorners(
            pool,
            toTransform,
            topLeftRadius,
            topRightRadius,
            bottomRightRadius,
            bottomLeftRadius
        ).run {
            val offset = (borderSize * 2).toInt()
            scale(width-offset,height-offset)
        }
        canvas.drawBitmap(scaleBitmap,borderSize,borderSize,null)
        scaleBitmap.recycle()
        return bitmap
    }
}
package com.lauter.androidappbases.common.transformation

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.ColorInt

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.TransformationUtils
import java.security.MessageDigest
import kotlin.math.min


data class CircleBorder(private val borderSize: Float, @ColorInt private val borderColor: Int) : BitmapTransformation() {

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update("${this::class.java.name}$borderSize$borderColor)".toByteArray(CHARSET))
    }

    override fun transform(
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap {
        val diam = min(outWidth,outHeight)
        val radius = diam/2f
        val bitmap = pool.get(diam, diam, Bitmap.Config.ARGB_8888)
        val borderOffset = (2*borderSize).toInt()
        val circleBitmap = TransformationUtils.circleCrop(pool,toTransform,
            outWidth-borderOffset,outHeight-borderOffset)
        val paint = Paint().apply {
            isAntiAlias = true
            style = Paint.Style.STROKE
            color = borderColor
            strokeWidth = borderSize
        }
        val canvas = Canvas(bitmap)
        canvas.drawBitmap(circleBitmap,borderSize,borderSize,null)
        canvas.drawCircle(
            radius,
            radius,
            radius - borderSize/2,
            paint
        )
        circleBitmap.recycle()
        return bitmap
    }
}
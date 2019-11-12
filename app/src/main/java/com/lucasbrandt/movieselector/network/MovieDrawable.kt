package com.lucasbrandt.movieselector.network

import android.graphics.*
import android.graphics.Paint.Align
import android.graphics.drawable.Drawable


class MovieDrawable(val text: String) : Drawable() {
    private val paint: Paint = Paint()

    init {
        paint.color = Color.WHITE
        paint.textSize = 52f
        paint.isAntiAlias = true
        paint.isFakeBoldText = true
        paint.setShadowLayer(12f, 0f, 0f, Color.BLACK)
        paint.style = Paint.Style.FILL
        paint.textAlign = Align.LEFT
    }

    override fun draw(canvas: Canvas) {
        val bounds = bounds
        canvas.drawText(
            text,
            bounds.centerX() - 15f * text.length,
            bounds.centerY() + 15f,
            paint
        )
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun setColorFilter(cf: ColorFilter?) {
        paint.colorFilter = cf
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }
}
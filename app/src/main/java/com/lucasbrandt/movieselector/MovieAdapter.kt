package com.lucasbrandt.movieselector

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.support.v4.content.ContextCompat
import com.lucasbrandt.movieselector.network.MovieDataModel
import com.lucasbrandt.movieselector.network.MovieDrawable
import com.lukedeighton.wheelview.adapter.WheelArrayAdapter

class MovieAdapter(val entries: List<MovieDataModel>, val context: Context) : WheelArrayAdapter<MovieDataModel>(entries) {

    override fun getDrawable(position: Int): Drawable {
        val drawable = arrayOf(createOvalDrawable(), MovieDrawable(position.toString()))
        return LayerDrawable(drawable)
    }

    private fun createOvalDrawable(): Drawable {
        val shapeDrawable = ShapeDrawable(OvalShape())
        shapeDrawable.paint.color = ContextCompat.getColor(context, R.color.background_material_dark)
        return shapeDrawable
    }
}

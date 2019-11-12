package com.lucasbrandt.movieselector

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import com.lucasbrandt.movieselector.network.MovieDataModel
import com.lucasbrandt.movieselector.network.MovieDrawable
import com.lukedeighton.wheelview.adapter.WheelArrayAdapter

class MovieAdapter(entries: List<MovieDataModel>?, context: Context) : WheelArrayAdapter<MovieDataModel>(entries) {

    private val colorArray = context.resources.getIntArray(R.array.ovalColors)

    override fun getDrawable(position: Int): Drawable {
        val drawable = arrayOf(createOvalDrawable(position), MovieDrawable(position.toString()))
        return LayerDrawable(drawable)
    }

    private fun createOvalDrawable(position: Int): Drawable {
        val shapeDrawable = ShapeDrawable(OvalShape())
        shapeDrawable.paint.color = colorArray[position]
        return shapeDrawable
    }
}

package com.lucasbrandt.movieselector

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import com.lucasbrandt.movieselector.network.MovieDataModel
import com.lukedeighton.wheelview.adapter.WheelArrayAdapter
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target


class MovieAdapter(val entries: List<MovieDataModel>?, val context: Context) : WheelArrayAdapter<MovieDataModel>(entries) {
    lateinit var drawable: Array<Drawable>

    val target = object : Target {
        override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
            drawable = arrayOf(createOvalDrawable(), BitmapDrawable(context.resources, bitmap))
        }

    private val colorArray = context.resources.getIntArray(R.array.ovalColors)
        override fun onBitmapFailed(errorDrawable: Drawable) {
            drawable = arrayOf(createOvalDrawable(), errorDrawable)
        }

        override fun onPrepareLoad(placeHolderDrawable: Drawable) {
            drawable = arrayOf(createOvalDrawable(), placeHolderDrawable)
        }
    }

    override fun getDrawable(position: Int): Drawable {

//        val target = object : Target {
//            override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
//                drawable = arrayOf(createOvalDrawable(), BitmapDrawable(context.resources, bitmap))
//            }
//
//            override fun onBitmapFailed(errorDrawable: Drawable) {
//                drawable = arrayOf(createOvalDrawable(), errorDrawable)
//            }
//
//            override fun onPrepareLoad(placeHolderDrawable: Drawable) {
//                drawable = arrayOf(createOvalDrawable(), placeHolderDrawable)
//            }
//        }

        Picasso.with(context)
            .load(IMG_URL + entries?.get(position)?.backdrop_path)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(target)

        return LayerDrawable(drawable)
    }

    private fun createOvalDrawable(): Drawable {
        return ShapeDrawable(OvalShape())
    }
}

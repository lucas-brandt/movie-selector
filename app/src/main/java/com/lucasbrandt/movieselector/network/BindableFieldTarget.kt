package com.lucasbrandt.movieselector.network

import android.content.res.Resources
import android.databinding.ObservableField
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target


class BindableFieldTarget(private val observableField: ObservableField<Drawable>, private val resources: Resources) : Target {

    override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
        observableField.set(BitmapDrawable(resources, bitmap))
    }

    override fun onBitmapFailed(errorDrawable: Drawable) {
        observableField.set(errorDrawable)
    }

    override fun onPrepareLoad(placeHolderDrawable: Drawable) {
        observableField.set(placeHolderDrawable)
    }
}
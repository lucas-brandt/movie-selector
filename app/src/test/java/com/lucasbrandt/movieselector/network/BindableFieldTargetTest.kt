package com.lucasbrandt.movieselector.network

import android.content.res.Resources
import android.databinding.ObservableField
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.squareup.picasso.Picasso
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class BindableFieldTargetTest {

    @Mock
    lateinit var resources: Resources

    //This is the primary subject of tests, therefore named "subject"
    lateinit var subject: BindableFieldTarget

    private var observableField: ObservableField<Drawable> = ObservableField()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        subject = BindableFieldTarget(observableField, resources)
    }

    //This test is arguably pointless, but I have to get the exact same BitmapDrawable
    //created in onBitmapLoaded otherwise the test will fail
    @Test
    fun onBitmapLoaded_observableFieldSetsBitmapDrawable() {
        val from: Picasso.LoadedFrom = Picasso.LoadedFrom.DISK
        val bitmap: Bitmap = mock(Bitmap::class.java)

        subject.onBitmapLoaded(bitmap, from)

        assertEquals(subject.observableField.get(), observableField.get())
    }

    @Test
    fun onBitmapFailed_observableFieldSetsErrorDrawable() {
        val drawable: Drawable = mock(Drawable::class.java)

        subject.onBitmapFailed(drawable)

        assertEquals(drawable, observableField.get())
    }

    @Test
    fun onBitmapLoaded_observableFieldSetsErrorDrawable() {
        val drawable: Drawable = mock(Drawable::class.java)

        subject.onPrepareLoad(drawable)

        assertEquals(drawable, observableField.get())
    }
}
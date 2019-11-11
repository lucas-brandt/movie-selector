package com.lucasbrandt.movieselector

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.ObservableField
import android.graphics.drawable.Drawable
import com.lucasbrandt.movieselector.network.BindableFieldTarget
import com.lucasbrandt.movieselector.network.MovieDataModel
import com.squareup.picasso.Picasso

class MovieInfoViewModel : ViewModel() {
    val movieTitle: ObservableField<String> = ObservableField("")
    val movieDescription: ObservableField<String> = ObservableField("")
    val movieRating: ObservableField<String> = ObservableField("")
    val movieReleaseDate: ObservableField<String> = ObservableField("")
    val moviePoster: ObservableField<Drawable> = ObservableField()

    lateinit var bindableFieldTarget: BindableFieldTarget

    fun setMovieInformation(movieInformation: MovieDataModel?) {
        movieTitle.set(movieInformation?.title)
        movieDescription.set(movieInformation?.overview)
        movieRating.set(movieInformation?.vote_average + MOVIE_RATING_APPEND)
        movieReleaseDate.set(movieInformation?.release_date)
    }

    fun setMoviePosterWithPicasso(context: Context, posterPath: String?) {
        bindableFieldTarget = BindableFieldTarget(moviePoster, context.resources)
        Picasso.with(context)
            .load(IMG_URL + posterPath)
            .placeholder(R.drawable.placeholder)
            .into(bindableFieldTarget)
    }
}

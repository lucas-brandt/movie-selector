package com.lucasbrandt.movieselector

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.lucasbrandt.movieselector.network.MovieDataModel

class MovieInfoViewModel : ViewModel() {
    val movieTitle: ObservableField<String> = ObservableField("")
    val movieDescription: ObservableField<String> = ObservableField("")
    val movieRating: ObservableField<String> = ObservableField("")
    val movieReleaseDate: ObservableField<String> = ObservableField("")

    fun setMovieInformation(movieInformation: MovieDataModel?) {
        movieTitle.set(movieInformation?.title)
        movieDescription.set(movieInformation?.overview)
        movieRating.set(movieInformation?.vote_average)
        movieReleaseDate.set(movieInformation?.release_date)
    }
}

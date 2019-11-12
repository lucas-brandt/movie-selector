package com.lucasbrandt.movieselector

import com.lucasbrandt.movieselector.network.MovieDataModel
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock

class MovieInfoViewModelTest {

    //This is the primary subject of tests, therefore named "subject"
    private val subject: MovieInfoViewModel = MovieInfoViewModel()

    @Test
    fun setMovieInformation_setsObservableFieldsCorrectly() {
        val mockMovieDataModel = mock(MovieDataModel::class.java)

        subject.setMovieInformation(mockMovieDataModel)
        assertEquals(mockMovieDataModel.title, subject.movieTitle.get())
        assertEquals(mockMovieDataModel.overview, subject.movieDescription.get())
        assertEquals(mockMovieDataModel.vote_average + "/10", subject.movieRating.get())
        assertEquals(mockMovieDataModel.release_date, subject.movieReleaseDate.get())
    }

    //There are no tests for setMoviePosterWithPicasso because testing Picasso is redundant
    //Picasso already has unit tests for Picasso.with() for instance
}
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
        assertEquals(subject.movieTitle.get(), mockMovieDataModel.title)
        assertEquals(subject.movieDescription.get(), mockMovieDataModel.overview)
        assertEquals(subject.movieRating.get(), mockMovieDataModel.vote_average + "/10")
        assertEquals(subject.movieReleaseDate.get(), mockMovieDataModel.release_date)
    }
}
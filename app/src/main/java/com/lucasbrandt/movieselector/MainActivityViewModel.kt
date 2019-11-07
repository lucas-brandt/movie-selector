package com.lucasbrandt.movieselector

import android.arch.lifecycle.ViewModel
import com.lucasbrandt.movieselector.network.MovieDataResponse
import com.lucasbrandt.movieselector.network.MovieService
import com.lucasbrandt.movieselector.network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {
    private val movieService: MovieService = RetrofitBuilder.createService()

    init {
        setMovieData()
    }

    fun setMovieData() {
        val call = movieService.getCurrentlyPlayingMovies(API_KEY)
        call.enqueue(object : Callback<MovieDataResponse> {
            override fun onResponse(call: Call<MovieDataResponse>, response: Response<MovieDataResponse>) {
                System.out.println(response.body()?.results?.get(0)?.title)
            }

            override fun onFailure(call: Call<MovieDataResponse>, t: Throwable) {
            }
        })

    }
}

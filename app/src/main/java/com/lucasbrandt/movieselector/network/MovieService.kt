package com.lucasbrandt.movieselector.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/now_playing")
    fun getCurrentlyPlayingMovies(@Query("api_key") apiKey : String): Call<MovieDataResponse>
}
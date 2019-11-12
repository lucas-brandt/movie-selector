package com.lucasbrandt.movieselector.network

class MovieDataResponse(val results: List<MovieDataModel>)

class MovieDataModel(
    val title: String,
    val overview: String,
    val release_date: String,
    val vote_average: String,
    val poster_path: String,
    val backdrop_path: String
)
package com.lucasbrandt.movieselector

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.lucasbrandt.movieselector.network.MovieDataModel
import com.lucasbrandt.movieselector.network.MovieDataResponse
import com.lucasbrandt.movieselector.network.MovieService
import com.lucasbrandt.movieselector.network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {
    private val movieService: MovieService = RetrofitBuilder.createService()
    val movieLiveList: MutableLiveData<ArrayList<MovieDataModel>> = MutableLiveData()
    val movieTitle: ObservableField<String> = ObservableField("")
    val movieDescription: ObservableField<String> = ObservableField("")

    init {
        getMovieData()
    }

    private fun getMovieData() {
        val call = movieService.getCurrentlyPlayingMovies(API_KEY)
        val movieList = ArrayList<MovieDataModel>(10)
        call.enqueue(object : Callback<MovieDataResponse> {
            override fun onResponse(call: Call<MovieDataResponse>, response: Response<MovieDataResponse>) {
                for (i in 0 until 10) {
                    response.body()?.results?.get(i)?.let { movieList.add(it) }
                }
                movieLiveList.postValue(movieList)
            }

            override fun onFailure(call: Call<MovieDataResponse>, t: Throwable) {
            }
        })
    }

    fun setMovieDetails(title: String, description: String) {
        movieTitle.set(title)
        movieDescription.set(description)
    }
}

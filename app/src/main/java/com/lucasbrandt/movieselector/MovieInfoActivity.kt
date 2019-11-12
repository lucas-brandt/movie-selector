package com.lucasbrandt.movieselector

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import com.lucasbrandt.movieselector.databinding.ActivityMovieInfoBinding
import com.lucasbrandt.movieselector.network.MovieDataModel


class MovieInfoActivity : AppCompatActivity() {

    lateinit var binding: ActivityMovieInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_info)
        val movieInfoViewModel = ViewModelProviders.of(this).get(MovieInfoViewModel::class.java)
        binding.viewModel = movieInfoViewModel

        val movieInformation =
            Gson().fromJson<MovieDataModel>(intent.getStringExtra(INTENT_NAME), MovieDataModel::class.java)
        movieInfoViewModel.setMovieInformation(movieInformation)
        movieInfoViewModel.setMoviePosterWithPicasso(this, movieInformation?.poster_path)
    }
}
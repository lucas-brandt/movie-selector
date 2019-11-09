package com.lucasbrandt.movieselector

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lucasbrandt.movieselector.databinding.ActivityMainBinding
import com.lucasbrandt.movieselector.network.MovieDataModel

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        binding.viewModel = mainActivityViewModel

        mainActivityViewModel.movieLiveList.observe(this, Observer { movies ->
            val movieList: ArrayList<MovieDataModel>? = movies
            binding.wheelView.adapter = movieList?.let { MovieAdapter(it, this) }
        })

        binding.wheelView.setOnWheelItemSelectedListener { parent, itemDrawable, position ->
            val selectedEntry = (parent.adapter as MovieAdapter).getItem(position)
            mainActivityViewModel.setMovieDetails(selectedEntry.title, selectedEntry.overview)
        }
    }
}

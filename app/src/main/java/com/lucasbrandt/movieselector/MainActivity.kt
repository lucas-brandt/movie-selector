package com.lucasbrandt.movieselector

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import com.lucasbrandt.movieselector.databinding.ActivityMainBinding
import com.lucasbrandt.movieselector.network.MovieDataModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        binding.viewModel = mainActivityViewModel

        mainActivityViewModel.movieLiveList.observe(this, Observer { movies ->
            val movieList: List<MovieDataModel>? = movies
            binding.wheelView.adapter = MovieAdapter(movieList, this)
        })

        binding.wheelView.setOnWheelItemSelectedListener { parent, itemDrawable, position ->
            val selectedEntry = (parent.adapter as MovieAdapter).getItem(position)
            mainActivityViewModel.setMovieDetails(selectedEntry?.title, selectedEntry?.overview)
            mainActivityViewModel.setMovieBackdropWithPicasso(this, selectedEntry?.backdrop_path)
        }

        binding.wheelView.setOnWheelItemClickListener { parent, position, isSelected ->
            val selectedEntry = (parent.adapter as MovieAdapter).getItem(position)
            val movieDataModelJson = Gson().toJson(selectedEntry)
            val intent = Intent(baseContext, MovieInfoActivity::class.java)
            intent.putExtra(INTENT_NAME, movieDataModelJson)
            startActivity(intent)
        }
    }
}

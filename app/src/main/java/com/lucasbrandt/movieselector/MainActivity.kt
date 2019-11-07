package com.lucasbrandt.movieselector

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lucasbrandt.movieselector.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        binding.viewModel = mainActivityViewModel
    }
}

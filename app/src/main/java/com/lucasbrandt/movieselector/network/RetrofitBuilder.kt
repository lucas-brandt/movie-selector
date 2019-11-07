package com.lucasbrandt.movieselector.network

import com.lucasbrandt.movieselector.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    companion object {

        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun createService(): MovieService {
            return retrofit.create<MovieService>(MovieService::class.java)
        }
    }
}
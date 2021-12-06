package com.saveo.movieapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieService {
    private val BASE_URL = "https://api.themoviedb.org/3/"

    fun getMovieServices() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiService: MovieAPI = getMovieServices().create(MovieAPI::class.java)
}
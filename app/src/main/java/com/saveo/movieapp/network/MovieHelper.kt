package com.saveo.movieapp.network

class MovieHelper(private val apiService: MovieAPI) {
    suspend fun getMovieList(appId: String) = apiService.getMovieList(appId)
    suspend fun getMovieDetails(id: Int, appId: String) = apiService.getMovieDetails(id,appId)
}
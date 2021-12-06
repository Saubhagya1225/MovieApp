package com.saveo.movieapp.network

class MovieRepo(private val apiHelper: MovieHelper) {
    suspend fun getMovieList(appId: String) = apiHelper.getMovieList(appId)
    suspend fun getMovieDetails(id: Int,appId: String) = apiHelper.getMovieDetails(id,appId)

}
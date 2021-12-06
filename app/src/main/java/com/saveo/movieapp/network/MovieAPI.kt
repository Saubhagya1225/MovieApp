package com.saveo.movieapp.network

import android.graphics.Movie
import com.saveo.movieapp.model.MovieAppData
import com.saveo.movieapp.model.MovieDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieAPI {

    @GET("movie/popular")
    suspend fun getMovieList(
        @Query("api_key") type: String
    ): Response<MovieAppData>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKEy: String?
    ): Response<MovieDetails?>?

    /*@GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") int id, @Query("api_key") type: String) : Response<MovieDetails>*/

}
package com.saveo.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.saveo.movieapp.network.MovieRepo
import com.saveo.movieapp.network.Resource
import kotlinx.coroutines.Dispatchers

class MovieViewModel(private val movieRepo: MovieRepo) : ViewModel() {
    fun getMovie() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = movieRepo.getMovieList("5a7761033deb96f52d8a9ed71b2779f4")))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun  getMovieDetails(id:Int) =liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = movieRepo.getMovieDetails(id,"5a7761033deb96f52d8a9ed71b2779f4")))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}
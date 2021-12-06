package com.saveo.movieapp.network

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saveo.movieapp.viewmodel.MovieViewModel

class ViewModelFactory(private val apiHelper: MovieHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(MovieRepo(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
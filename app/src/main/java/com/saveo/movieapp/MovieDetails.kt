package com.saveo.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.widget.ViewPager2
import com.saveo.movieapp.adapter.MovieAdapter
import com.saveo.movieapp.adapter.ViewPagerAdapter
import com.saveo.movieapp.model.Result
import com.saveo.movieapp.network.MovieHelper
import com.saveo.movieapp.network.MovieService
import com.saveo.movieapp.network.ViewModelFactory
import com.saveo.movieapp.viewmodel.MovieViewModel
import com.squareup.picasso.Picasso

class MovieDetails : AppCompatActivity() {
    private lateinit var movieModel: MovieViewModel
    private lateinit var movieImg : AppCompatImageView
    private lateinit var movieTitle : AppCompatTextView
    private lateinit var movieRelease : AppCompatTextView
    private lateinit var movieRatingAvg : AppCompatTextView
    private lateinit var movieDetails : AppCompatTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        movieImg = findViewById(R.id.movie_img)
        movieTitle = findViewById(R.id.tv_movie_name)
        movieTitle = findViewById(R.id.tv_movie_name)
        movieRelease = findViewById(R.id.tv_release_date)
        movieRatingAvg = findViewById(R.id.tv_rating)
        movieDetails = findViewById(R.id.tv_details)

        val movieDet = intent.getSerializableExtra("MOVIE") as? Result
        movieModel = ViewModelProviders.of(
            this,
            ViewModelFactory(MovieHelper(MovieService.apiService))
        ).get(MovieViewModel::class.java)

        Picasso.with(this).load("https://image.tmdb.org/t/p/w500/" + movieDet?.poster_path)
            .fit().centerCrop()
            .into(movieImg);
        movieTitle.text = movieDet?.title
        movieRelease.text = movieDet?.release_date
        movieRatingAvg.text = movieDet?.vote_average.toString()
        movieDetails.text = movieDet?.overview

       // getMovieDetails(movieId)


    }

    /*private fun getMovieDetails(movieId: Int) {
        movieModel.getMovieDetails(movieId).observe(this, Observer {

            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        val result = it.data?.body()
                        Picasso.with(this).load("https://image.tmdb.org/t/p/w500/" + result?.poster_path)
                            .fit().centerCrop()
                            .into(movieImg);
                        movieTitle.text = result?.title


                        Toast.makeText(this, result.toString(), Toast.LENGTH_SHORT).show()
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    }
                    Status.LOADING -> {

                    }
                }


            }

        })
    }*/
}
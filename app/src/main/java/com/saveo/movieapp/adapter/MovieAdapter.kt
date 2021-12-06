package com.saveo.movieapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.saveo.movieapp.R
import com.saveo.movieapp.model.Result
import com.squareup.picasso.Picasso

class MovieAdapter(
    private val context: Context,
    private val movieValue: List<Result>,
    private val condition: ViewPagerAdapter.ConditionViewPager
) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movieImg: AppCompatImageView = view.findViewById(R.id.img_poster)
        fun bind(result: Result) {
            Picasso.with(context).load("https://image.tmdb.org/t/p/w500/" + result.poster_path)
                .fit().centerCrop()
                .into(movieImg);
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_list, parent, false)
    )

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) {
        holder.bind(movieValue[position])
        holder.movieImg.setOnClickListener {
            condition.condition(position,movieValue[position])
        }
    }

    override fun getItemCount(): Int {
        return movieValue.size
    }

    interface ConditionViewPager {

        fun condition(position: Int, moviedetails: Result)

    }
}
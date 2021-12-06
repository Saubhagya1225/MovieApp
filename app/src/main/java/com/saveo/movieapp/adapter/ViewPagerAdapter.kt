package com.saveo.movieapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.saveo.movieapp.R
import com.saveo.movieapp.model.MovieAppData
import com.saveo.movieapp.model.Result
import com.saveo.movieapp.ui.home.HomeFragment
import com.squareup.picasso.Picasso

class ViewPagerAdapter(
    private val context: Context,
    private val movieValue: List<Result>,
    private val condition: ConditionViewPager
) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {
    inner class ViewPagerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val movieImg: AppCompatImageView = view.findViewById(R.id.img_movie)
        fun bind(movieData: Result) {
            Picasso.with(context).load("https://image.tmdb.org/t/p/w500/" + movieData.poster_path)
                .fit().centerCrop()
                .into(movieImg);
        }
        //val txt: TextView = view.findViewById(R.id.textView)

    }

    interface ConditionViewPager {

        fun condition(position: Int, moviedetails: Result)

    }

    /* override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder =
         ViewPagerViewHolder(
             LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
         )*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.ViewPagerViewHolder, position: Int) {
        holder.bind(movieValue[position])

    }

    override fun getItemCount(): Int {
        return movieValue.size
    }

    /*  fun updateTransactionList(newMovieList: List<MovieAppData>){
          movieValue.clear()
          movieValue.addAll(newMovieList)
          notifyDataSetChanged()
      }*/
}
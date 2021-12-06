package com.saveo.movieapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.saveo.movieapp.MovieDetails
import com.saveo.movieapp.R
import com.saveo.movieapp.Status
import com.saveo.movieapp.adapter.MovieAdapter
import com.saveo.movieapp.adapter.ViewPagerAdapter
import com.saveo.movieapp.model.Result
import com.saveo.movieapp.network.MovieHelper
import com.saveo.movieapp.network.MovieService
import com.saveo.movieapp.network.ViewModelFactory
import com.saveo.movieapp.viewmodel.MovieViewModel

class HomeFragment : Fragment(), ViewPagerAdapter.ConditionViewPager {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var viewPager: ViewPager2
    private lateinit var recyclerView: RecyclerView
    private lateinit var movieModel: MovieViewModel

    var planImageList: ArrayList<Result> = ArrayList<Result>()
    var movieList: ArrayList<Result> = ArrayList<Result>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
       // val textView: TextView = root.findViewById(R.id.text_home)
        viewPager = root.findViewById(R.id.movie_viewpager)
        recyclerView = root.findViewById(R.id.recycler)

        homeViewModel.text.observe(viewLifecycleOwner, Observer {
           // textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // viewPager = view.findViewById(R.id.movie_viewpager)

        movieModel = ViewModelProviders.of(
            this,
            ViewModelFactory(MovieHelper(MovieService.apiService))
        ).get(MovieViewModel::class.java)
        viewPager.adapter = activity?.let { ViewPagerAdapter(it, listOf(), this) }
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        val layoutManager = GridLayoutManager(context, 3)
        recyclerView.setLayoutManager(layoutManager);

        observeViewModel()


    }

    private fun observeViewModel() {
        movieModel.getMovie().observe(viewLifecycleOwner, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        val result = it.data?.body()
                        val res1 = result?.results
                        planImageList = it.data?.body()?.results as ArrayList<Result>
                        viewPager.adapter =
                            activity?.let { ViewPagerAdapter(it, planImageList, this) }
                        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                        recyclerView.adapter =
                            context?.let { it1 -> MovieAdapter(it1, planImageList, this) }
                        //Toast.makeText(context, result.toString(), Toast.LENGTH_SHORT).show()
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    }
                    Status.LOADING -> {

                    }
                }


            }
        })
    }

    override fun condition(position: Int, moviedetails: Result) {
        Toast.makeText(context, id.toString(), Toast.LENGTH_SHORT).show()
        val intent = Intent(context,MovieDetails::class.java)
        intent.putExtra("MOVIE",moviedetails)
        startActivity(intent)
    }

}
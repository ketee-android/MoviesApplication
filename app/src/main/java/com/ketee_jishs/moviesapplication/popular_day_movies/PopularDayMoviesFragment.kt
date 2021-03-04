package com.ketee_jishs.moviesapplication.popular_day_movies

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ketee_jishs.moviesapplication.R
import com.ketee_jishs.moviesapplication.activities.InfoActivity
import com.ketee_jishs.moviesapplication.adapter.ItemMovie
import com.ketee_jishs.moviesapplication.adapter.RecyclerViewPopularDayAdapter
import com.ketee_jishs.moviesapplication.databinding.FragmentPopularDayMoviesBinding

@Suppress("DEPRECATION")
@RequiresApi(Build.VERSION_CODES.N)
class PopularDayMoviesFragment : Fragment(), RecyclerViewPopularDayAdapter.OnItemClickListener {
    lateinit var binding: FragmentPopularDayMoviesBinding
    private val adapter = RecyclerViewPopularDayAdapter(arrayListOf(), this)

    companion object {
        var popularDayIsVisible = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: PopularDayMoviesViewModel by lazy {
            ViewModelProviders.of(this).get(PopularDayMoviesViewModel::class.java)
        }
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_popular_day_movies, container, false)
        binding.viewModel = viewModel

        binding.executePendingBindings()

        binding.recyclerPopularDayMoviesView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerPopularDayMoviesView.adapter = adapter
        viewModel.upcomingMovies.observe(
            this,
            Observer<ArrayList<ItemMovie>> { it?.let { adapter.replaceData(it) } })
        viewModel.loadUpcomingMovies()

        viewModel.setVisibility(popularDayIsVisible)

        return binding.root
    }

    override fun onItemClick(filmId: String, position: Int) {
        startActivity(Intent(context, InfoActivity::class.java))
    }
}

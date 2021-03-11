package com.ketee_jishs.moviesapplication.popular_day_movies

import android.content.Context
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
import com.ketee_jishs.moviesapplication.utils.KEY_DAY_POPULAR
import com.ketee_jishs.moviesapplication.utils.POPULAR_DAY_INVISIBLE
import com.ketee_jishs.moviesapplication.utils.POPULAR_DAY_VISIBLE
import com.ketee_jishs.moviesapplication.utils.PREFS_SWITCH_BUTTONS_NAME

@Suppress("DEPRECATION")
@RequiresApi(Build.VERSION_CODES.N)
class PopularDayMoviesFragment : Fragment(), RecyclerViewPopularDayAdapter.OnItemClickListener {
    private val sharedPrefs by lazy {activity?.getSharedPreferences(PREFS_SWITCH_BUTTONS_NAME, Context.MODE_PRIVATE)}
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
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_popular_day_movies, container, false)
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
        viewModel.popularDayMovies.observe(
            this,
            Observer<ArrayList<ItemMovie>> { it?.let { adapter.replaceData(it) } })
        viewModel.loadPopularDayMovies()

        initSettings()
        viewModel.setVisibility(popularDayIsVisible)

        return binding.root
    }

    override fun onItemClick(filmId: String, position: Int) {
        startActivity(Intent(context, InfoActivity::class.java))
    }

    private fun initSettings() {
        when (getSavedDayPopular()) {
            POPULAR_DAY_VISIBLE -> popularDayIsVisible = false
            POPULAR_DAY_INVISIBLE -> popularDayIsVisible = true
        }
    }

    private fun getSavedDayPopular() = sharedPrefs?.getInt(KEY_DAY_POPULAR, POPULAR_DAY_VISIBLE)
}

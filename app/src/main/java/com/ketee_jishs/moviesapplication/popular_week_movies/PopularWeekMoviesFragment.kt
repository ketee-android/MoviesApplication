package com.ketee_jishs.moviesapplication.popular_week_movies

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
import com.ketee_jishs.moviesapplication.adapter.RecyclerViewMoviesAdapter
import com.ketee_jishs.moviesapplication.databinding.FragmentPopularWeekMoviesBinding
import com.ketee_jishs.moviesapplication.utils.KEY_WEEK_POPULAR
import com.ketee_jishs.moviesapplication.utils.POPULAR_WEEK_INVISIBLE
import com.ketee_jishs.moviesapplication.utils.POPULAR_WEEK_VISIBLE
import com.ketee_jishs.moviesapplication.utils.PREFS_SWITCH_BUTTONS_NAME

@RequiresApi(Build.VERSION_CODES.N)
class PopularWeekMoviesFragment : Fragment(), RecyclerViewMoviesAdapter.OnItemClickListener {
    private val sharedPrefs by lazy {activity?.getSharedPreferences(PREFS_SWITCH_BUTTONS_NAME, Context.MODE_PRIVATE)}
    lateinit var binding: FragmentPopularWeekMoviesBinding
    private val adapterWeek = RecyclerViewMoviesAdapter(arrayListOf(), this)

    companion object {
        var popularWeekIsVisible = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_popular_week_movies, container, false)
        val viewModel: PopularWeekMoviesViewModel by lazy {
            ViewModelProviders.of(this).get(PopularWeekMoviesViewModel::class.java)
        }
        binding.viewModel = viewModel
        binding.executePendingBindings()

        binding.recyclerPopularWeekMoviesView.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = adapterWeek
        }

        viewModel.popularMovies.observe(
            this,
            Observer<ArrayList<ItemMovie>> { it?.let { adapterWeek.replaceData(it) } })
        viewModel.loadPopularWeekMovies()

        initSettings()
        viewModel.setVisibility(popularWeekIsVisible)

        return binding.root
    }

    override fun onItemClick(filmId: String, position: Int) {
        startActivity(Intent(context, InfoActivity::class.java))
    }

    private fun initSettings() {
        when (getSavedWeekPopular()) {
            POPULAR_WEEK_VISIBLE -> popularWeekIsVisible = false
            POPULAR_WEEK_INVISIBLE -> popularWeekIsVisible = true
        }
    }

    private fun getSavedWeekPopular() = sharedPrefs?.getInt(KEY_WEEK_POPULAR, POPULAR_WEEK_VISIBLE)
}

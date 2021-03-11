package com.ketee_jishs.moviesapplication.top_rated_movies

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
import com.ketee_jishs.moviesapplication.adapter.RecyclerViewTopRatedAdapter
import com.ketee_jishs.moviesapplication.databinding.FragmentTopRatedMoviesBinding
import com.ketee_jishs.moviesapplication.utils.KEY_TOP_RATED
import com.ketee_jishs.moviesapplication.utils.PREFS_SWITCH_BUTTONS_NAME
import com.ketee_jishs.moviesapplication.utils.TOP_RATED_INVISIBLE
import com.ketee_jishs.moviesapplication.utils.TOP_RATED_VISIBLE

@RequiresApi(Build.VERSION_CODES.N)
class TopRatedMoviesFragment : Fragment(), RecyclerViewTopRatedAdapter.OnItemClickListener {
    private val sharedPrefs by lazy {
        activity?.getSharedPreferences(
            PREFS_SWITCH_BUTTONS_NAME,
            Context.MODE_PRIVATE
        )
    }
    lateinit var binding: FragmentTopRatedMoviesBinding
    private val adapter = RecyclerViewTopRatedAdapter(arrayListOf(), this)

    companion object {
        var topRatedIsVisible = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_top_rated_movies, container, false)
        val viewModel: TopRatedViewModel by lazy {
            ViewModelProviders.of(this).get(TopRatedViewModel::class.java)
        }
        binding.viewModel = viewModel
        binding.executePendingBindings()

        binding.recyclerTopRatedMoviesView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerTopRatedMoviesView.adapter = adapter
        viewModel.topRatedFilms.observe(
            this,
            Observer<ArrayList<ItemMovie>> { it?.let { adapter.replaceData(it) } })
        viewModel.loadTopRatedMovies()

        initSettings()
        viewModel.setVisibility(topRatedIsVisible)

        return binding.root
    }

    override fun onItemClick(filmId: String, position: Int) {
        startActivity(Intent(context, InfoActivity::class.java))
    }

    private fun initSettings() {
        when (getSavedTopRated()) {
            TOP_RATED_VISIBLE -> topRatedIsVisible = false
            TOP_RATED_INVISIBLE -> topRatedIsVisible = true
        }
    }

    private fun getSavedTopRated() = sharedPrefs?.getInt(KEY_TOP_RATED, TOP_RATED_VISIBLE)
}
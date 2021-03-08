package com.ketee_jishs.moviesapplication.popular_week_movies

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
import com.ketee_jishs.moviesapplication.adapter.RecyclerViewPopularWeekAdapter
import com.ketee_jishs.moviesapplication.databinding.FragmentPopularWeekMoviesBinding

@RequiresApi(Build.VERSION_CODES.N)
class PopularWeekMoviesFragment : Fragment(), RecyclerViewPopularWeekAdapter.OnItemClickListener {
    lateinit var binding: FragmentPopularWeekMoviesBinding
    private val adapter = RecyclerViewPopularWeekAdapter(arrayListOf(), this)

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

        binding.recyclerPopularWeekMoviesView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerPopularWeekMoviesView.adapter = adapter

        viewModel.popularMovies.observe(
            this,
            Observer<ArrayList<ItemMovie>> { it?.let { adapter.replaceData(it) } })
        viewModel.loadPopularMovies()
        viewModel.setVisibility(popularWeekIsVisible)
        return binding.root
    }

    override fun onItemClick(filmId: String, position: Int) {
        startActivity(Intent(context, InfoActivity::class.java))
    }
}
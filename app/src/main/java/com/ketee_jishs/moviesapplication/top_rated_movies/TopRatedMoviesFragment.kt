package com.ketee_jishs.moviesapplication.top_rated_movies

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

@RequiresApi(Build.VERSION_CODES.N)
class TopRatedMoviesFragment : Fragment(), RecyclerViewTopRatedAdapter.OnItemClickListener {
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
        viewModel.setVisibility(topRatedIsVisible)

        return binding.root
    }

    override fun onItemClick(filmId: String, position: Int) {
        startActivity(Intent(context, InfoActivity::class.java))
    }
}
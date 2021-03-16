package com.ketee_jishs.moviesapplication.recommendations

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
import com.ketee_jishs.moviesapplication.databinding.FragmentRecommendationsBinding
import com.ketee_jishs.moviesapplication.info.InfoFragment

class RecommendationsFragment : Fragment() , RecyclerViewMoviesAdapter.OnItemClickListener {
    lateinit var binding: FragmentRecommendationsBinding
    private val adapterRecommendations = RecyclerViewMoviesAdapter(arrayListOf(), this)

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_recommendations, container,false)
        val viewModel: RecommendationsViewModel by lazy {
            ViewModelProviders.of(this).get(RecommendationsViewModel::class.java)
        }
        binding.viewModel = viewModel

        binding.executePendingBindings()

        binding.recyclerRecommendationsView.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = adapterRecommendations
        }

        viewModel.recommendations.observe(
            this,
            Observer<ArrayList<ItemMovie>> { it?.let { adapterRecommendations.replaceData(it) } })

        viewModel.loadRecommendations(InfoFragment.idFilm)

        return binding.root
    }

    override fun onItemClick(filmId: String, position: Int) {
        startActivity(Intent(context, InfoActivity::class.java))
    }
}
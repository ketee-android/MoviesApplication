package com.ketee_jishs.moviesapplication.adventures_films

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ketee_jishs.moviesapplication.R
import com.ketee_jishs.moviesapplication.activities.InfoActivity
import com.ketee_jishs.moviesapplication.adapter.ItemFilm
import com.ketee_jishs.moviesapplication.adapter.RecyclerViewAdventureAdapter
import com.ketee_jishs.moviesapplication.databinding.FragmentAdventuresFilmsBinding

class AdventuresFilmsFragment : Fragment(), RecyclerViewAdventureAdapter.OnItemClickListener {
    lateinit var binding: FragmentAdventuresFilmsBinding
    private val adapter = RecyclerViewAdventureAdapter(arrayListOf(), this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_adventures_films, container, false)
        val viewModel: AdventuresFilmsViewModel by lazy {
            ViewModelProviders.of(this).get(AdventuresFilmsViewModel::class.java)
        }
        binding.viewModel = viewModel
        binding.executePendingBindings()

        binding.recyclerAdventuresFilmsView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerAdventuresFilmsView.adapter = adapter
        viewModel.adventuresFilms.observe(
            this,
            Observer<ArrayList<ItemFilm>> { it?.let { adapter.replaceData(it) } })
        viewModel.loadAdventuresFilms()

        return binding.root
    }

    override fun onItemClick(filmId: String, position: Int) {
        startActivity(Intent(context, InfoActivity::class.java))
    }
}
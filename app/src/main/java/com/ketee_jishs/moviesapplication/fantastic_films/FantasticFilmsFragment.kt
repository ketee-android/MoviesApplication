package com.ketee_jishs.moviesapplication.fantastic_films

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
import com.ketee_jishs.moviesapplication.InfoActivity
import com.ketee_jishs.moviesapplication.R
import com.ketee_jishs.moviesapplication.adapter.ItemFilm
import com.ketee_jishs.moviesapplication.adapter.RecyclerViewAdapter
import com.ketee_jishs.moviesapplication.databinding.FragmentFantasticFilmsBinding

class FantasticFilmsFragment : Fragment(), RecyclerViewAdapter.OnItemClickListener {
    lateinit var binding: FragmentFantasticFilmsBinding
    private val adapter = RecyclerViewAdapter(arrayListOf(), this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fantastic_films, container, false)
        val viewModel = ViewModelProviders.of(this).get(FantasticFilmsViewModel::class.java)
        binding.viewModel = viewModel
        binding.executePendingBindings()

        binding.recyclerFantasticFilmsView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerFantasticFilmsView.adapter = adapter
        viewModel.fantasticFilms.observe(this, Observer<ArrayList<ItemFilm>> {it?.let { adapter.replaceData(it)} })
        viewModel.loadFantasticFilms()

        return binding.root
    }

    override fun onItemClick(position: Int) {
        startActivity(Intent(context, InfoActivity::class.java))
    }
}
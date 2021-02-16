package com.ketee_jishs.moviesapplication.horror_films

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
import com.ketee_jishs.moviesapplication.databinding.FragmentHorrorFilmsBinding

class HorrorFilmsFragment : Fragment(), RecyclerViewAdapter.OnItemClickListener {
    lateinit var binding: FragmentHorrorFilmsBinding
    private val adapter = RecyclerViewAdapter(arrayListOf(), this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_horror_films, container, false)
        val viewModel = ViewModelProviders.of(this).get(HorrorFilmsViewModel::class.java)
        binding.viewModel = viewModel
        binding.executePendingBindings()

        binding.recyclerHorrorFilmsView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerHorrorFilmsView.adapter = adapter
        viewModel.horrorFilms.observe(this, Observer<ArrayList<ItemFilm>> {it?.let { adapter.replaceData(it)} })
        viewModel.loadHorrorFilms()

        return binding.root
    }

    override fun onItemClick(filmId: String, position: Int) {
        startActivity(Intent(context, InfoActivity::class.java))
    }
}

package com.ketee_jishs.moviesapplication.history

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ketee_jishs.moviesapplication.R
import com.ketee_jishs.moviesapplication.activities.InfoActivity
import com.ketee_jishs.moviesapplication.adapter.HistoryAdapter
import com.ketee_jishs.moviesapplication.databinding.FragmentHistoryBinding
import com.ketee_jishs.moviesapplication.info.AppState

class HistoryFragment : Fragment(), HistoryAdapter.OnItemClickListener {
    lateinit var binding: FragmentHistoryBinding
    private val adapter = HistoryAdapter(arrayListOf(), this)
    val viewModel: HistoryViewModel by lazy {
        ViewModelProviders.of(this).get(HistoryViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)
        binding.viewModel = viewModel
        binding.executePendingBindings()

        binding.historyRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.historyRecyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.historyLiveData.observe(viewLifecycleOwner, Observer {renderData(it)})
        viewModel.getAllHistory()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                adapter.replaceData(appState.movieData)
                viewModel.setProgressBarVisibility(true)
            }
            is AppState.Error -> {
                Toast.makeText(context, "Ошибка загрузки данных", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onItemClick(filmId: String, position: Int) {
        startActivity(Intent(context, InfoActivity::class.java))
    }
}
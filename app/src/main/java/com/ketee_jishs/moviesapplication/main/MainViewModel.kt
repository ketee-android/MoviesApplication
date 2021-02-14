package com.ketee_jishs.moviesapplication.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ketee_jishs.moviesapplication.adapter.ItemFilm

class MainViewModel : ViewModel() {
    private var repoMainModel: RepoMainModel = RepoMainModel()
    var films = MutableLiveData<ArrayList<ItemFilm>>()

    fun loadFilms() {
        repoMainModel.getFilms(object : OnFilmReadyCallback {
            override fun onDataReady(data: ArrayList<ItemFilm>) {
                films.value = data
            }
        })
    }
}
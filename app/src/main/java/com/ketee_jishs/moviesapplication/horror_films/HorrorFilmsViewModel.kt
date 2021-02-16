package com.ketee_jishs.moviesapplication.horror_films

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ketee_jishs.moviesapplication.adapter.ItemFilm
import com.ketee_jishs.moviesapplication.adapter.OnFilmReadyCallback
import com.ketee_jishs.moviesapplication.adapter.RepoMainModel

class HorrorFilmsViewModel: ViewModel() {
    private var repoMainModel: RepoMainModel = RepoMainModel()
    var horrorFilms = MutableLiveData<ArrayList<ItemFilm>>()

    fun loadHorrorFilms() {
        repoMainModel.getHorrorFilms(object : OnFilmReadyCallback {
            override fun onDataReady(data: ArrayList<ItemFilm>) {
                horrorFilms.value = data
            }
        })
    }
}
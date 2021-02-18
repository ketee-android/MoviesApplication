package com.ketee_jishs.moviesapplication.adventures_films

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ketee_jishs.moviesapplication.adapter.ItemFilm
import com.ketee_jishs.moviesapplication.adapter.OnFilmReadyCallback
import com.ketee_jishs.moviesapplication.adapter.RepoMainModel

class AdventuresFilmsViewModel : ViewModel() {
    private var repoMainModel: RepoMainModel = RepoMainModel()
    var adventuresFilms = MutableLiveData<ArrayList<ItemFilm>>()

    fun loadAdventuresFilms() {
        repoMainModel.getAdventuresFilms(object :
            OnFilmReadyCallback {
            override fun onDataReady(data: ArrayList<ItemFilm>) {
                adventuresFilms.value = data
            }
        })
    }
}
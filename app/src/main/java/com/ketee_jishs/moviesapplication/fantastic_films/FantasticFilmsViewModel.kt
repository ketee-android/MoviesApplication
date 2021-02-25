package com.ketee_jishs.moviesapplication.fantastic_films

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ketee_jishs.moviesapplication.adapter.ItemFilm
import com.ketee_jishs.moviesapplication.repository.OnFilmReadyCallback
import com.ketee_jishs.moviesapplication.repository.RepoMainModel

@RequiresApi(Build.VERSION_CODES.N)
class FantasticFilmsViewModel : ViewModel() {
    private var repoMainModel: RepoMainModel =
        RepoMainModel()
    var fantasticFilms = MutableLiveData<ArrayList<ItemFilm>>()

    fun loadFantasticFilms() {
        repoMainModel.getFantasticFilms(object :
            OnFilmReadyCallback {
            override fun onDataReady(data: ArrayList<ItemFilm>) {
                fantasticFilms.value = data
            }
        })
    }
}
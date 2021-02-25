package com.ketee_jishs.moviesapplication.adventures_films

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ketee_jishs.moviesapplication.adapter.ItemFilm
import com.ketee_jishs.moviesapplication.repository.OnFilmReadyCallback
import com.ketee_jishs.moviesapplication.repository.RepoMainModel

@RequiresApi(Build.VERSION_CODES.N)
class AdventuresFilmsViewModel : ViewModel() {
    private var repoMainModel: RepoMainModel =
        RepoMainModel()
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
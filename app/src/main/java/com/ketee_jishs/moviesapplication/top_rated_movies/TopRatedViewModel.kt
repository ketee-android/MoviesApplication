package com.ketee_jishs.moviesapplication.top_rated_movies

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ketee_jishs.moviesapplication.adapter.ItemMovie
import com.ketee_jishs.moviesapplication.repository.OnMovieReadyCallback
import com.ketee_jishs.moviesapplication.repository.RepoMainModel

@RequiresApi(Build.VERSION_CODES.N)
class TopRatedViewModel : ViewModel() {
    private var repoMainModel: RepoMainModel =
        RepoMainModel()
    var topRatedFilms = MutableLiveData<ArrayList<ItemMovie>>()
    var visibility = ObservableField<Boolean>(false)

    fun loadTopRatedMovies() {
        repoMainModel.getTopRatedMovies(object :
            OnMovieReadyCallback {
            override fun onDataReady(data: ArrayList<ItemMovie>) {
                topRatedFilms.value = data
            }
        })
    }

    fun setVisibility(isVisible: Boolean) {
        visibility.set(isVisible)
    }
}
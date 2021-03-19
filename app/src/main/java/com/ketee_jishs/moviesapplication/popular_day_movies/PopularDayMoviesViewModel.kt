package com.ketee_jishs.moviesapplication.popular_day_movies

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ketee_jishs.moviesapplication.adapter.ItemMovie
import com.ketee_jishs.moviesapplication.repository.OnMovieReadyCallback
import com.ketee_jishs.moviesapplication.repository.RepoMainModel
import com.ketee_jishs.moviesapplication.utils.POPULAR_MOVIES_DAY_URL

@RequiresApi(Build.VERSION_CODES.N)
class PopularDayMoviesViewModel : ViewModel() {
    private var repoMainModel: RepoMainModel =
        RepoMainModel()
    var popularDayMovies = MutableLiveData<ArrayList<ItemMovie>>()
    var visibility = ObservableField<Boolean>(false)

    fun loadPopularDayMovies() {
        repoMainModel.getInfo(object :
            OnMovieReadyCallback {
            override fun onDataReady(data: ArrayList<ItemMovie>) {
                popularDayMovies.value = data
            }
        }, POPULAR_MOVIES_DAY_URL)
    }

    fun setVisibility(isVisible: Boolean) {
        visibility.set(isVisible)
    }
}

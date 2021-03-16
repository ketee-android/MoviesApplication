package com.ketee_jishs.moviesapplication.popular_week_movies

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ketee_jishs.moviesapplication.adapter.ItemMovie
import com.ketee_jishs.moviesapplication.repository.OnMovieReadyCallback
import com.ketee_jishs.moviesapplication.repository.RepoMainModel
import com.ketee_jishs.moviesapplication.utils.POPULAR_MOVIES_WEEK_URL

@RequiresApi(Build.VERSION_CODES.N)
class PopularWeekMoviesViewModel : ViewModel() {
    private var repoMainModel: RepoMainModel =
        RepoMainModel()
    var popularMovies = MutableLiveData<ArrayList<ItemMovie>>()
    var visibility = ObservableField<Boolean>(false)

    fun loadPopularWeekMovies() {
        repoMainModel.getInfo(object :
            OnMovieReadyCallback {
            override fun onDataReady(data: ArrayList<ItemMovie>) {
                popularMovies.value = data
            }
        }, POPULAR_MOVIES_WEEK_URL)
    }

    fun setVisibility(isVisible: Boolean) {
        visibility.set(isVisible)
    }
}

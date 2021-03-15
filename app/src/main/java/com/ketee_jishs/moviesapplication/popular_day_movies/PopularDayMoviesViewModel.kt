package com.ketee_jishs.moviesapplication.popular_day_movies

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ketee_jishs.moviesapplication.adapter.ItemMovie
import com.ketee_jishs.moviesapplication.main_recyclers.RemoteRecyclerDayDataSource
import com.ketee_jishs.moviesapplication.repository.OnMovieReadyCallback
import com.ketee_jishs.moviesapplication.repository.RecyclerDayRepositoryImpl
import com.ketee_jishs.moviesapplication.repository.RepoMainModel

@RequiresApi(Build.VERSION_CODES.N)
class PopularDayMoviesViewModel : ViewModel() {
    private var repoMainModel: RepoMainModel =
        RepoMainModel()
    var popularDayMovies = MutableLiveData<ArrayList<ItemMovie>>()
    val detailsRepositoryImpl: RecyclerDayRepositoryImpl = RecyclerDayRepositoryImpl(
        RemoteRecyclerDayDataSource()
    )
    var visibility = ObservableField<Boolean>(false)

    fun loadPopularDayMovies() {
//        detailsRepositoryImpl.getMovieInfoFromServer(callBack)
        repoMainModel.getPopularDayMovies(object :
            OnMovieReadyCallback {
            override fun onDataReady(data: ArrayList<ItemMovie>) {
                popularDayMovies.value = data
            }
        })
    }

    fun setVisibility(isVisible: Boolean) {
        visibility.set(isVisible)
    }

//    private val callBack = object : Callback<RecyclerMoviesDTO> {
//        override fun onResponse(
//            call: Call<RecyclerMoviesDTO>,
//            response: Response<RecyclerMoviesDTO>
//        ) {
//            val serverResponse: RecyclerMoviesDTO? = response.body()
//            popularDayMovies.postValue(
//                if (response.isSuccessful && serverResponse != null) {
//                    convertDtoToRecyclerModel(serverResponse)
//                } else {
//                    null
//                }
//            )
//        }
//
//        override fun onFailure(call: Call<RecyclerMoviesDTO>, t: Throwable) {
//            Throwable(t.message ?: "REQUEST_ERROR")
//        }
//    }
}
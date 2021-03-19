package com.ketee_jishs.moviesapplication.recommendations

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ketee_jishs.moviesapplication.adapter.ItemMovie

@RequiresApi(Build.VERSION_CODES.N)
class RecommendationsViewModel : ViewModel() {
    private var repoRecommendationsModel: RepoRecommendationsModel =
        RepoRecommendationsModel()
    var recommendations = MutableLiveData<ArrayList<ItemMovie>>()

    fun loadRecommendations(id: String) {
        repoRecommendationsModel.getInfo(object :
            OnRecommendationsReadyCallback {
            override fun onDataReady(data: ArrayList<ItemMovie>) {
                recommendations.value = data
            }
        }, id)
    }
}
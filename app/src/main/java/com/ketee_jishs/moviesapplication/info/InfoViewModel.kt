package com.ketee_jishs.moviesapplication.info

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

@RequiresApi(Build.VERSION_CODES.N)
class InfoViewModel : ViewModel() {
    private val repoInfoModel: RepoInfoModel = RepoInfoModel()

    var filmName = ObservableField<String>()
    var originalTitle = ObservableField<String>()
    var rating = ObservableField<String>()
    var time = ObservableField<String>()
    var description = ObservableField<String>()
    var overview = ObservableField<String>()
    var isVisible = ObservableField(false)

    fun loadInfoForFilm(itemId: String) {
        repoInfoModel.getInfoForFilm(itemId, object : OnInfoReadyCallback {
            override fun onInfoReady(
                nameReady: String,
                originalTitleReady: String,
                ratingReady: String,
                timeReady: String,
                descriptionReady: String,
                overviewReady: String
            ) {
                filmName.set(nameReady)
                originalTitle.set(originalTitleReady)
                rating.set(ratingReady)
                time.set(timeReady)
                description.set(descriptionReady)
                overview.set(overviewReady)
                isVisible.set(true)
            }
        })
    }
}
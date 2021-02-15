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
    var year = ObservableField<String>()
    var country = ObservableField<String>()
    var genres = ObservableField<String>()
    var overview = ObservableField<String>()

//    var textInteractor: TextInteractor = TextInteractorImpl()

    fun loadInfoForFilm() {
        repoInfoModel.getInfoForFilm("348", object : OnInfoReadyCallback {
            override fun onInfoReady(nameReady: String, originalTitleReady: String, ratingReady: String, timeReady: String,
                                     yearReady: String, countryReady: String, genresReady: String, overviewReady: String) {

                filmName.set(nameReady)
                originalTitle.set(originalTitleReady)
                rating.set(ratingReady)
                time.set(timeReady)
                year.set(yearReady)
                country.set(countryReady)
                genres.set(genresReady)
                overview.set(overviewReady)
            }
        })
    }
}
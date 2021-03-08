package com.ketee_jishs.moviesapplication.info

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ketee_jishs.moviesapplication.App.Companion.getHistoryDao
import com.ketee_jishs.moviesapplication.movie_data.MovieDTO
import com.ketee_jishs.moviesapplication.repository.DetailsRepositoryImpl
import com.ketee_jishs.moviesapplication.repository.LocalRepository
import com.ketee_jishs.moviesapplication.repository.LocalRepositoryImpl
import com.ketee_jishs.moviesapplication.repository.RemoteDataSource
import com.ketee_jishs.moviesapplication.utils.convertDtoToInfoModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"
private const val CORRUPTED_DATA = "Неполные данные"

@Suppress("SENSELESS_COMPARISON")
@RequiresApi(Build.VERSION_CODES.N)
class InfoViewModel(
    val detailsLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val detailsRepositoryImpl: DetailsRepositoryImpl = DetailsRepositoryImpl(RemoteDataSource()),
    private val historyRepository: LocalRepository = LocalRepositoryImpl(getHistoryDao())
) : ViewModel() {
    var filmName = ObservableField<String>()
    var originalTitle = ObservableField<String>()
    var rating = ObservableField<String>()
    var time = ObservableField<String>()
    var description = ObservableField<String>()
    var overview = ObservableField<String>()
    var poster = ObservableField<Uri>()
    var isVisible = ObservableField(false)
    var movieId = ObservableField<Int>()

    fun setInfoForFilm(
        id: Int,
        nameInfo: String,
        originalTitleInfo: String,
        ratingInfo: String,
        timeInfo: String,
        descriptionInfo: String,
        overviewInfo: String,
        posterInfo: Uri,
        isVisibleInfo: Boolean
    ) {
        movieId.set(id)
        filmName.set(nameInfo)
        originalTitle.set(originalTitleInfo)
        rating.set(ratingInfo)
        time.set(timeInfo)
        description.set(descriptionInfo)
        overview.set(overviewInfo)
        poster.set(posterInfo)
        isVisible.set(isVisibleInfo)
    }

    fun saveMovieToDB(infoList: InfoList) {
        historyRepository.saveEntity(infoList)
    }

    fun getFilmInfoFromRemoteSource(id: String) {
        detailsRepositoryImpl.getMovieInfoFromServer(id, callBack)
    }

    private val callBack = object : Callback<MovieDTO> {
        override fun onResponse(call: Call<MovieDTO>, response: Response<MovieDTO>) {
            val serverResponse: MovieDTO? = response.body()
            detailsLiveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    getCheckedResponse(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<MovieDTO>, t: Throwable) {
            detailsLiveData.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }
    }

    private fun getCheckedResponse(serverResponse: MovieDTO): AppState {
        val title = serverResponse.title
        val originalTitle = serverResponse.originalTitle
        val voteAverage = serverResponse.voteAverage
        val runtime = serverResponse.runtime
        val releaseDate = serverResponse.releaseDate
        val genres = serverResponse.genres[0]
        val country = serverResponse.productionCountries[0]
        val overview = serverResponse.overview
        return if (title == null || originalTitle == null || voteAverage == null || runtime == null
            || releaseDate == null || genres == null || country == null || overview == null
        ) {
            AppState.Error(Throwable(CORRUPTED_DATA))
        } else {
            AppState.Success(convertDtoToInfoModel(serverResponse))
        }
    }
}
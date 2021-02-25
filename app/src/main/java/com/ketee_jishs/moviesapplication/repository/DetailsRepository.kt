package com.ketee_jishs.moviesapplication.repository

import com.ketee_jishs.moviesapplication.film_data.FilmDTO
import retrofit2.Callback

class DetailsRepositoryImpl (private val remoteDataSource: RemoteDataSource) : DetailsRepository {
    override fun getFilmInfoFromServer(
        id: String,
        callback: Callback<FilmDTO>
    ) {
        remoteDataSource.getFilmDetails(id, callback)
    }
}

interface DetailsRepository {
    fun getFilmInfoFromServer(
        id: String,
        callback: Callback<FilmDTO>
    )
}
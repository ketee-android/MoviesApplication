package com.ketee_jishs.moviesapplication.repository

import com.ketee_jishs.moviesapplication.movie_data.MovieDTO
import retrofit2.Callback

class DetailsRepositoryImpl (private val remoteDataSource: RemoteDataSource) : DetailsRepository {
    override fun getMovieInfoFromServer(
        id: String,
        callback: Callback<MovieDTO>
    ) {
        remoteDataSource.getMovieDetails(id, callback)
    }
}

interface DetailsRepository {
    fun getMovieInfoFromServer(
        id: String,
        callback: Callback<MovieDTO>
    )
}
package com.ketee_jishs.moviesapplication.repository

import com.ketee_jishs.moviesapplication.main_recyclers.RemoteRecyclerDayDataSource
import com.ketee_jishs.moviesapplication.main_recyclers.RemoteRecyclerTopDataSource
import com.ketee_jishs.moviesapplication.main_recyclers.RemoteRecyclerWeekDataSource
import com.ketee_jishs.moviesapplication.movie_data.RecyclerMoviesDTO
import retrofit2.Callback

class RecyclerDayRepositoryImpl (private val remoteRecyclerDayDataSource: RemoteRecyclerDayDataSource) : RecyclersDayRepository {
    override fun getMovieInfoFromServer(
        callback: Callback<RecyclerMoviesDTO>
    ) {
        remoteRecyclerDayDataSource.getPopularDayDetails(callback)
    }
}

class RecyclerWeekRepositoryImpl (private val remoteRecyclerWeekDataSource: RemoteRecyclerWeekDataSource) : RecyclerWeekRepository {
    override fun getMovieInfoFromServer(
        callback: Callback<RecyclerMoviesDTO>
    ) {
        remoteRecyclerWeekDataSource.getPopularWeekDetails(callback)
    }
}

class RecyclerTopRepositoryImpl (private val remoteRecyclerTopDataSource: RemoteRecyclerTopDataSource) : RecyclerTopRepository {
    override fun getMovieInfoFromServer(
        callback: Callback<RecyclerMoviesDTO>
    ) {
        remoteRecyclerTopDataSource.getTopRatedDetails(callback)
    }
}

interface RecyclersDayRepository {
    fun getMovieInfoFromServer(
        callback: Callback<RecyclerMoviesDTO>
    )
}

interface RecyclerWeekRepository {
    fun getMovieInfoFromServer(
        callback: Callback<RecyclerMoviesDTO>
    )
}

interface RecyclerTopRepository {
    fun getMovieInfoFromServer(
        callback: Callback<RecyclerMoviesDTO>
    )
}
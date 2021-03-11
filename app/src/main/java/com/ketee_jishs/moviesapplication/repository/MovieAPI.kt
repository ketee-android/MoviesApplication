package com.ketee_jishs.moviesapplication.repository

import com.ketee_jishs.moviesapplication.movie_data.MovieDTO
import com.ketee_jishs.moviesapplication.movie_data.RecyclerMoviesDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {
    @GET("3/movie/{item_id}")
    fun getMovie(
        @Path(value = "item_id") itemId: String,
        @Query(value = "api_key") token: String,
        @Query(value = "language") lang: String
    ): Call<MovieDTO>
}

interface PopularDayApi {
    @GET("3/trending/movies/day")
    fun getPopularDayMovies(
        @Query(value = "api_key") token: String,
        @Query(value = "language") lang: String
    ): Call<RecyclerMoviesDTO>
}

interface PopularWeekApi {
    @GET("3/trending/movies/week")
    fun getPopularWeekMovies(
        @Query(value = "api_key") token: String,
        @Query(value = "language") lang: String
    ): Call<RecyclerMoviesDTO>
}

interface TopRatedApi {
    @GET("3/movie/top_rated")
    fun getTopRatedMovies(
        @Query(value = "api_key") token: String,
        @Query(value = "language") lang: String
    ): Call<RecyclerMoviesDTO>
}
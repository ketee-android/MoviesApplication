package com.ketee_jishs.moviesapplication.repository

import com.ketee_jishs.moviesapplication.film_data.FilmDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmAPI {
    @GET("3/movie/{item_id}")
    fun getFilm(
        @Path(value = "item_id") itemId: String,
        @Query(value = "api_key") token: String,
        @Query(value = "language") lang: String
    ): Call<FilmDTO>
}
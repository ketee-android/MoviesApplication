package com.ketee_jishs.moviesapplication.movie_data

import com.google.gson.annotations.SerializedName

class RecyclerMoviesDTO(
    @SerializedName("results") val results: ArrayList<Results>
) {
    companion object {
        val results: ArrayList<Results> = arrayListOf()
    }
}

data class Results(
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("id") val id: Int,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("title") val title: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("vote_average") val voteAverage: Double
)
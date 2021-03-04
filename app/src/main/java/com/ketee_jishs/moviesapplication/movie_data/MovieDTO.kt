package com.ketee_jishs.moviesapplication.movie_data

import com.google.gson.annotations.SerializedName

data class MovieDTO(
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("genres") val genres: ArrayList<Genres>,
    @SerializedName("id") val id: Int,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("production_countries") val productionCountries: ArrayList<ProductionCountry>,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("runtime") val runtime: Int,
    @SerializedName("title") val title: String,
    @SerializedName("vote_average") val voteAverage: Double
)

data class Genres(
    @SerializedName("name") val name: String
)

data class ProductionCountry(
    @SerializedName("iso_3166_1") var countryName: String
)
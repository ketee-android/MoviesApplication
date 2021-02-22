package com.ketee_jishs.moviesapplication.film_request

data class FilmRequest(
    val genres: ArrayList<Genres>,
    val id: Int,
    val original_title: String,
    val overview: String,
    val production_countries: ArrayList<ProductionCountry>,
    val release_date: String,
    val runtime: Int,
    val title: String,
    val vote_average: Double
)

data class Genres(
    val name: String
)

data class ProductionCountry(
    var iso_3166_1: String
)
package com.ketee_jishs.moviesapplication.film_request

class FilmRequest(
    var backdrop_path: String,
    var budget: Int,
    var genres: ArrayList<Genres>,
    var homepage: String,
    var id: Int,
    var original_title: String,
    var overview: String,
    var poster_path: String,
    var production_companies: ArrayList<ProductionCompany>,
    var production_countries: ArrayList<ProductionCountry>,
    var release_date: String,
    var revenue: Int,
    var runtime: Int,
    var title: String,
    var video: Boolean,
    var vote_average: Double
)
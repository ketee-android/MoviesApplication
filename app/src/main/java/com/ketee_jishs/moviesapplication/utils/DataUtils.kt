package com.ketee_jishs.moviesapplication.utils

import com.ketee_jishs.moviesapplication.info.InfoFragment
import com.ketee_jishs.moviesapplication.info.InfoList
import com.ketee_jishs.moviesapplication.movie_data.MovieDTO

fun convertDtoToInfoModel(movieDTO: MovieDTO): List<InfoList> {
    val simpleTime = movieDTO.runtime.toString()
    val time = "$simpleTime мин"

    val stringBuilderYear = StringBuilder()
    val simpleYear =
        stringBuilderYear.append(movieDTO.releaseDate).delete(4, 10).toString()
    val year = "$simpleYear,"

    val country = movieDTO.productionCountries[0].countryName

    val stringBuilderGenres = StringBuilder()
    for (i in movieDTO.genres.indices) {
        stringBuilderGenres.append(", ").append(movieDTO.genres[i].name)
    }
    val genres = stringBuilderGenres.toString()

    val description = "$year $country$genres"

    val comment = InfoFragment.commentForMovieText

    return listOf(
        InfoList(
            movieDTO.id,
            movieDTO.title,
            movieDTO.originalTitle,
            movieDTO.voteAverage.toString(),
            time,
            description,
            movieDTO.overview,
            movieDTO.posterPath,
            comment
        )
    )
}
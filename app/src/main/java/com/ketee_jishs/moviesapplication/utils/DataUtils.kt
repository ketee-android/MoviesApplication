package com.ketee_jishs.moviesapplication.utils

import com.ketee_jishs.moviesapplication.film_data.FilmDTO
import com.ketee_jishs.moviesapplication.info.InfoList

fun convertDtoToInfoModel(filmDTO: FilmDTO): List<InfoList> {
    val simpleTime = filmDTO.runtime.toString()
    val time = "$simpleTime мин"

    val stringBuilderYear = StringBuilder()
    val simpleYear =
        stringBuilderYear.append(filmDTO.releaseDate).delete(4, 10).toString()
    val year = "$simpleYear,"

    val country = filmDTO.productionCountries[0].countryName

    val stringBuilderGenres = StringBuilder()
    for (i in filmDTO.genres.indices) {
        stringBuilderGenres.append(", ").append(filmDTO.genres[i].name)
    }
    val genres = stringBuilderGenres.toString()

    val description = "$year $country$genres"

    return listOf(
        InfoList(
            filmDTO.title,
            filmDTO.originalTitle,
            filmDTO.voteAverage.toString(),
            time,
            description,
            filmDTO.overview,
            filmDTO.posterPath
        )
    )
}
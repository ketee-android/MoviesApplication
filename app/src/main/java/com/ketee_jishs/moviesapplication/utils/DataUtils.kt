package com.ketee_jishs.moviesapplication.utils

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

    return listOf(
        InfoList(
            movieDTO.id,
            movieDTO.title,
            movieDTO.originalTitle,
            movieDTO.voteAverage.toString(),
            time,
            description,
            movieDTO.overview,
            movieDTO.posterPath
        )
    )
}

//fun convertDtoToRecyclerModel(recyclerMoviesDTO: RecyclerMoviesDTO?): ArrayList<ItemMovie> {
//    for (i in 0..19) {
//        val filmName = recyclerMoviesDTO!!.results[i].title
//        val id = recyclerMoviesDTO.results[i].id.toString()
//        val rating = recyclerMoviesDTO.results[i].voteAverage.toString()
//        val stringBuilderYear = StringBuilder()
//        val year =
//            stringBuilderYear.append(recyclerMoviesDTO.results[i].releaseDate)
//                .delete(4, 10).toString()
//        val poster =
//            Uri.parse("https://image.tmdb.org/t/p/w500${recyclerMoviesDTO.results[i].posterPath}")
//        val adult = recyclerMoviesDTO.results[i].adult
//
//        return arrayListOf(
//            ItemMovie(
//                filmName, id, year, rating, poster, adult
//            )
//        )
//    }
//}


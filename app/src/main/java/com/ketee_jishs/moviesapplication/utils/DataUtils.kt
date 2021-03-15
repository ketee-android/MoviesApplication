package com.ketee_jishs.moviesapplication.utils

import android.net.Uri
import android.os.Handler
import android.os.Looper
import com.ketee_jishs.moviesapplication.adapter.ItemMovie
import com.ketee_jishs.moviesapplication.info.InfoList
import com.ketee_jishs.moviesapplication.movie_data.MovieDTO
import com.ketee_jishs.moviesapplication.movie_data.RecyclerMoviesDTO

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

fun convertDtoToRecyclerModel(onMovieCallback: OnMovieCallback) {
    var movies: ArrayList<ItemMovie> = ArrayList()
    val results by lazy { RecyclerMoviesDTO.results }
    val handler = Handler(Looper.getMainLooper())

    for (i in results.indices) {
        val filmName = results[i].title
        val id = results[i].id.toString()
        val rating = results[i].voteAverage.toString()
        val stringBuilderYear = StringBuilder()
        val year =
            stringBuilderYear.append(results[i].releaseDate)
                .delete(4, 10).toString()
        val poster =
            Uri.parse("https://image.tmdb.org/t/p/w500${results[i].posterPath}")
        val adult = results[i].adult

        movies.add(ItemMovie(filmName, id, year, rating, poster, adult))

        handler.post {
            onMovieCallback.onDataReady(movies)
        }
    }
}

interface OnMovieCallback {
    fun onDataReady(data: ArrayList<ItemMovie>)
}


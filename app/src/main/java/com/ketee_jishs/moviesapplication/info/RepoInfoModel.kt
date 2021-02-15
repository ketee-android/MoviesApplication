package com.ketee_jishs.moviesapplication.info

import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.ketee_jishs.moviesapplication.BuildConfig
import com.ketee_jishs.moviesapplication.film_request.FilmRequest
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

@Suppress("DEPRECATION")
@RequiresApi(Build.VERSION_CODES.N)
class RepoInfoModel {
    private var tag = "FILM"
    private var urlFirst = "https://api.themoviedb.org/3/movie/"
    private var urlSecond = "?api_key="
    private var urlThird = "&language=ru"

    fun getInfoForFilm(itemId: String, onInfoReadyCallback: OnInfoReadyCallback) {
        try {
            val uri = URL(urlFirst + itemId + urlSecond + BuildConfig.FILMS_API_KEY + urlThird)
            val handler: Handler = Handler(Looper.getMainLooper())

            Thread {
                var urlConnection: HttpsURLConnection? = null
                try {
                    urlConnection = uri.openConnection() as HttpsURLConnection?;
                    urlConnection?.requestMethod = "GET"
                    urlConnection?.readTimeout = 1000

                    val reader = BufferedReader(InputStreamReader(urlConnection?.inputStream))
                    val result: String? = getLines(reader)

                    val gson = Gson()
                    val filmRequest: FilmRequest = gson.fromJson(result, FilmRequest::class.java)

                    val filmName: String = String.format(filmRequest.title)
                    val originalTitle: String = String.format(filmRequest.original_title)
                    val rating: String = String.format(filmRequest.vote_average.toString())

                    val stringBuilderTime = StringBuilder()
                    stringBuilderTime.append(filmRequest.runtime.toString()).append(" мин")
                    val time: String = String.format(stringBuilderTime.toString())

                    val stringBuilderYear = StringBuilder()
                    stringBuilderYear.append(filmRequest.release_date).delete(4, 10).append(", ")
                    val year: String = String.format(stringBuilderYear.toString())

                    val country: String = String.format(filmRequest.production_countries[0].iso_3166_1)

                    val stringBuilderGenres = StringBuilder()
                    for (i in filmRequest.genres.indices) {
                        stringBuilderGenres.append(", ").append(filmRequest.genres[i].name)
                    }
                    val genres: String = String.format(stringBuilderGenres.toString())

                    val overview: String = String.format(filmRequest.overview)

                    handler.post {
                        onInfoReadyCallback.onInfoReady(
                            filmName, originalTitle, rating, time,
                            year, country, genres, overview
                        )
                    }

                } catch (e: Exception) {
                    Log.e(tag, "Fail connection", e)
                    e.printStackTrace()
                } finally {
                    urlConnection?.disconnect()
                }
            }.start()
        } catch (e: MalformedURLException) {
            Log.e(tag, "Fail URL", e)
        }
    }

    private fun getLines(reader: BufferedReader): String? {
        return reader.lines().collect(Collectors.joining("\n"))
    }
}

interface OnInfoReadyCallback {
    fun onInfoReady(
        nameReady: String,
        originalTitleReady: String,
        ratingReady: String,
        timeReady: String,
        yearReady: String,
        countryReady: String,
        genresReady: String,
        overviewReady: String
    )
}
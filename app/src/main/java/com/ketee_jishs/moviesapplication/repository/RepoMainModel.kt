package com.ketee_jishs.moviesapplication.repository

import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.ketee_jishs.moviesapplication.BuildConfig
import com.ketee_jishs.moviesapplication.adapter.ItemFilm
import com.ketee_jishs.moviesapplication.film_data.FilmDTO
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.*
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

const val tag = "FILM"
const val urlFirst = "https://api.themoviedb.org/3/movie/"
const val urlSecond = "?api_key="
const val urlThird = "&language=ru"

@Suppress("DEPRECATION")
@RequiresApi(Build.VERSION_CODES.N)
class RepoMainModel {
    var fantasticFilms: ArrayList<ItemFilm> = ArrayList(10)
    var horrorFilms: ArrayList<ItemFilm> = ArrayList(10)
    var adventuresFilms: ArrayList<ItemFilm> = ArrayList(10)

    fun getFantasticFilms(onFilmReadyCallback: OnFilmReadyCallback) {
        val idList = ArrayList<String> (listOf("348", "1895", "152", "603", "671", "1726", "1771", "155", "218", "431"))

        for (itemId in idList) {
            try {
                val uri = URL(urlFirst + itemId + urlSecond + BuildConfig.FILMS_API_KEY + urlThird)
                val handler = Handler(Looper.getMainLooper())
                Thread {
                    var urlConnection: HttpsURLConnection? = null
                    try {
                        urlConnection = uri.openConnection() as HttpsURLConnection?
                        urlConnection?.requestMethod = "GET"
                        urlConnection?.readTimeout = 1000

                        val reader = BufferedReader(InputStreamReader(urlConnection?.inputStream))
                        val result: String? = getLines(reader)

                        val gson = Gson()
                        val filmDTO: FilmDTO = gson.fromJson(result, FilmDTO::class.java)

                        val filmName = filmDTO.title
                        val rating = filmDTO.voteAverage.toString()
                        val stringBuilderYear = StringBuilder()
                        val year = stringBuilderYear.append(filmDTO.releaseDate).delete(4, 10).toString()
                        val poster = Uri.parse("https://image.tmdb.org/t/p/w500${filmDTO.posterPath}")

                        fantasticFilms.add(ItemFilm(filmName, itemId, year, rating, poster))

                        handler.post {
                            onFilmReadyCallback.onDataReady(fantasticFilms)
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
    }

    fun getHorrorFilms(onFilmReadyCallback: OnFilmReadyCallback) {
        val idList = ArrayList<String> (listOf("176", "694", "170", "924", "78", "2667", "806", "565", "805", "609"))

        for (itemId in idList) {
            try {
                val uri = URL(urlFirst + itemId + urlSecond + BuildConfig.FILMS_API_KEY + urlThird)
                val handler = Handler(Looper.getMainLooper())
                Thread {
                    var urlConnection: HttpsURLConnection? = null
                    try {
                        urlConnection = uri.openConnection() as HttpsURLConnection?
                        urlConnection?.requestMethod = "GET"
                        urlConnection?.readTimeout = 1000

                        val reader = BufferedReader(InputStreamReader(urlConnection?.inputStream))
                        val result: String? = getLines(reader)

                        val gson = Gson()
                        val filmDTO: FilmDTO = gson.fromJson(result, FilmDTO::class.java)

                        val filmName = filmDTO.title
                        val rating = filmDTO.voteAverage.toString()
                        val stringBuilderYear = StringBuilder()
                        val year = stringBuilderYear.append(filmDTO.releaseDate).delete(4, 10).toString()
                        val poster = Uri.parse("https://image.tmdb.org/t/p/w500${filmDTO.posterPath}")

                        horrorFilms.add(ItemFilm(filmName, itemId, year, rating, poster))

                        handler.post {
                            onFilmReadyCallback.onDataReady(horrorFilms)
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
    }

    fun getAdventuresFilms(onFilmReadyCallback: OnFilmReadyCallback) {
        val idList = ArrayList<String> (listOf("105", "22", "329", "85", "18", "254", "564", "861", "2501", "954"))

        for (itemId in idList) {
            try {
                val uri = URL(urlFirst + itemId + urlSecond + BuildConfig.FILMS_API_KEY + urlThird)
                val handler = Handler(Looper.getMainLooper())
                Thread {
                    var urlConnection: HttpsURLConnection? = null
                    try {
                        urlConnection = uri.openConnection() as HttpsURLConnection?
                        urlConnection?.requestMethod = "GET"
                        urlConnection?.readTimeout = 1000

                        val reader = BufferedReader(InputStreamReader(urlConnection?.inputStream))
                        val result: String? = getLines(reader)

                        val gson = Gson()
                        val filmDTO: FilmDTO = gson.fromJson(result, FilmDTO::class.java)

                        val filmName = filmDTO.title
                        val rating = filmDTO.voteAverage.toString()
                        val stringBuilderYear = StringBuilder()
                        val year = stringBuilderYear.append(filmDTO.releaseDate).delete(4, 10).toString()
                        val poster = Uri.parse("https://image.tmdb.org/t/p/w500${filmDTO.posterPath}")


                        adventuresFilms.add(ItemFilm(filmName, itemId, year, rating, poster))

                        handler.post {
                            onFilmReadyCallback.onDataReady(adventuresFilms)
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
    }

    private fun getLines(reader: BufferedReader): String? {
        return reader.lines().collect(Collectors.joining("\n"))
    }


}

interface OnFilmReadyCallback {
    fun onDataReady(data : ArrayList<ItemFilm>)
}
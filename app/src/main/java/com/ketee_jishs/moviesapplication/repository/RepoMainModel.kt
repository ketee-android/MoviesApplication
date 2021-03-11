package com.ketee_jishs.moviesapplication.repository

import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.ketee_jishs.moviesapplication.BuildConfig
import com.ketee_jishs.moviesapplication.adapter.ItemMovie
import com.ketee_jishs.moviesapplication.movie_data.RecyclerMoviesDTO
import com.ketee_jishs.moviesapplication.utils.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

@Suppress("DEPRECATION")
@RequiresApi(Build.VERSION_CODES.N)
class RepoMainModel {
    var popularDayMovies: ArrayList<ItemMovie> = ArrayList()
    var popularWeekMovies: ArrayList<ItemMovie> = ArrayList()
    var topRatedMovies: ArrayList<ItemMovie> = ArrayList()

    fun getPopularDayMovies(onMovieReadyCallback: OnMovieReadyCallback) {
        for (page in 1..3) {
            for (i in 0..19) {
                try {
                    val uri = URL(POPULAR_MOVIES_DAY_URL + BuildConfig.MOVIES_API_KEY + URL_LANGUAGE + URL_PAGE + page.toString())
                    val handler = Handler(Looper.getMainLooper())
                    Thread {
                        var urlConnection: HttpsURLConnection? = null
                        try {
                            urlConnection = uri.openConnection() as HttpsURLConnection?
                            urlConnection?.requestMethod = "GET"
                            urlConnection?.readTimeout = 10000

                            val reader = BufferedReader(InputStreamReader(urlConnection?.inputStream))
                            val result: String? = getLines(reader)

                            val gson = Gson()
                            val recyclerMoviesDTO = gson.fromJson(result, RecyclerMoviesDTO::class.java)

                            val filmName = recyclerMoviesDTO.results[i].title
                            val id = recyclerMoviesDTO.results[i].id.toString()
                            val rating = recyclerMoviesDTO.results[i].voteAverage.toString()
                            val stringBuilderYear = StringBuilder()
                            val year =
                                stringBuilderYear.append(recyclerMoviesDTO.results[i].releaseDate)
                                    .delete(4, 10).toString()
                            val poster =
                                Uri.parse("https://image.tmdb.org/t/p/w500${recyclerMoviesDTO.results[i].posterPath}")
                            val adult = recyclerMoviesDTO.results[i].adult

                            popularDayMovies.add(ItemMovie(filmName, id, year, rating, poster, adult))

                            handler.post {
                                onMovieReadyCallback.onDataReady(popularDayMovies)
                            }

                        } catch (e: Exception) {
                            Log.e(TAG, "Fail connection", e)
                            e.printStackTrace()
                        } finally {
                            urlConnection?.disconnect()
                        }
                    }.start()

                } catch (e: MalformedURLException) {
                    Log.e(TAG, "Fail URL", e)
                }
            }
        }
    }

    fun getPopularWeekMovies(onMovieReadyCallback: OnMovieReadyCallback) {
        for (page in 1..2) {
            for (i in 0..19) {
                try {
                    val uri = URL(POPULAR_MOVIES_WEEK_URL + BuildConfig.MOVIES_API_KEY + URL_LANGUAGE + URL_PAGE + page.toString())
                    val handler = Handler(Looper.getMainLooper())
                    Thread {
                        var urlConnection: HttpsURLConnection? = null
                        try {
                            urlConnection = uri.openConnection() as HttpsURLConnection?
                            urlConnection?.requestMethod = "GET"
                            urlConnection?.readTimeout = 10000

                            val reader = BufferedReader(InputStreamReader(urlConnection?.inputStream))
                            val result: String? = getLines(reader)

                            val gson = Gson()
                            val recyclerMoviesDTO = gson.fromJson(result, RecyclerMoviesDTO::class.java)

                            val filmName = recyclerMoviesDTO.results[i].title
                            val id = recyclerMoviesDTO.results[i].id.toString()
                            val rating = recyclerMoviesDTO.results[i].voteAverage.toString()
                            val stringBuilderYear = StringBuilder()
                            val year =
                                stringBuilderYear.append(recyclerMoviesDTO.results[i].releaseDate)
                                    .delete(4, 10).toString()
                            val poster =
                                Uri.parse("https://image.tmdb.org/t/p/w500${recyclerMoviesDTO.results[i].posterPath}")
                            val adult = recyclerMoviesDTO.results[i].adult

                            popularWeekMovies.add(ItemMovie(filmName, id, year, rating, poster, adult))

                            handler.post {
                                onMovieReadyCallback.onDataReady(popularWeekMovies)
                            }

                        } catch (e: Exception) {
                            Log.e(TAG, "Fail connection", e)
                            e.printStackTrace()
                        } finally {
                            urlConnection?.disconnect()
                        }
                    }.start()

                } catch (e: MalformedURLException) {
                    Log.e(TAG, "Fail URL", e)
                }
            }
        }
    }

    fun getTopRatedMovies(onMovieReadyCallback: OnMovieReadyCallback) {
        for (page in 1..3) {
            for (i in 0..19) {
                try {
                    val uri = URL(TOP_RATED_MOVIES_URL + BuildConfig.MOVIES_API_KEY + URL_LANGUAGE + URL_PAGE + page.toString())
                    val handler = Handler(Looper.getMainLooper())
                    Thread {
                        var urlConnection: HttpsURLConnection? = null
                        try {
                            urlConnection = uri.openConnection() as HttpsURLConnection?
                            urlConnection?.requestMethod = "GET"
                            urlConnection?.readTimeout = 10000

                            val reader = BufferedReader(InputStreamReader(urlConnection?.inputStream))
                            val result: String? = getLines(reader)

                            val gson = Gson()
                            val recyclerMoviesDTO = gson.fromJson(result, RecyclerMoviesDTO::class.java)

                            val filmName = recyclerMoviesDTO.results[i].title
                            val id = recyclerMoviesDTO.results[i].id.toString()
                            val rating = recyclerMoviesDTO.results[i].voteAverage.toString()
                            val stringBuilderYear = StringBuilder()
                            val year =
                                stringBuilderYear.append(recyclerMoviesDTO.results[i].releaseDate)
                                    .delete(4, 10).toString()
                            val poster =
                                Uri.parse("https://image.tmdb.org/t/p/w500${recyclerMoviesDTO.results[i].posterPath}")
                            val adult = recyclerMoviesDTO.results[i].adult

                            topRatedMovies.add(ItemMovie(filmName, id, year, rating, poster, adult))

                            handler.post {
                                onMovieReadyCallback.onDataReady(topRatedMovies)
                            }

                        } catch (e: Exception) {
                            Log.e(TAG, "Fail connection", e)
                            e.printStackTrace()
                        } finally {
                            urlConnection?.disconnect()
                        }
                    }.start()

                } catch (e: MalformedURLException) {
                    Log.e(TAG, "Fail URL", e)
                }
            }
        }
    }

    private fun getLines(reader: BufferedReader): String? {
        return reader.lines().collect(Collectors.joining("\n"))
    }
}

interface OnMovieReadyCallback {
    fun onDataReady(data: ArrayList<ItemMovie>)
}

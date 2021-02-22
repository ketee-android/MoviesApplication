@file:Suppress("DEPRECATION")

package com.ketee_jishs.moviesapplication.info

import android.app.IntentService
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.gson.Gson
import com.ketee_jishs.moviesapplication.BuildConfig
import com.ketee_jishs.moviesapplication.film_request.FilmRequest
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

const val ID_EXTRA = "id"
private const val REQUEST_GET = "GET"
private const val REQUEST_TIMEOUT = 10000

@RequiresApi(Build.VERSION_CODES.N)
class DetailsService(name: String = "DetailsService") : IntentService(name) {
    private val broadcastIntent = Intent(DETAILS_INTENT_FILTER)

    override fun onHandleIntent(intent: Intent?) {
        intent?.let {
            val id = intent.getStringExtra(ID_EXTRA)
            if (id == null) {
                onEmptyData()
            } else {
                loadInfo(id)
            }
        } ?: run { onEmptyIntent() }
    }

    private fun loadInfo(itemId: String) {
        try {
            val uri =
                URL("https://api.themoviedb.org/3/movie/${itemId}?api_key=${BuildConfig.FILMS_API_KEY}&language=ru")
            lateinit var urlConnection: HttpsURLConnection
            val handler = Handler(Looper.getMainLooper())
            Thread {
                try {
                    urlConnection = uri.openConnection() as HttpsURLConnection
                    urlConnection.apply {
                        requestMethod = REQUEST_GET
                        readTimeout = REQUEST_TIMEOUT
                    }

                    val filmRequest: FilmRequest =
                        Gson().fromJson(
                            getLines(BufferedReader(InputStreamReader(urlConnection.inputStream))),
                            FilmRequest::class.java
                        )
                    handler.post { onResponse(filmRequest) }

                } catch (e: Exception) {
                    onErrorRequest(e.message ?: "Empty error")
                } finally {
                    urlConnection.disconnect()
                }
            }.start()

        } catch (e: MalformedURLException) {
            onMalformedURL()
        }
    }

    private fun getLines(reader: BufferedReader): String? {
        return reader.lines().collect(Collectors.joining("\n"))
    }

    private fun onResponse(filmRequest: FilmRequest) {
        filmRequest.let {
            val simpleTime = filmRequest.runtime.toString()
            val time = "$simpleTime мин"

            val stringBuilderYear = StringBuilder()
            val simpleYear =
                stringBuilderYear.append(filmRequest.release_date).delete(4, 10).toString()
            val year = "$simpleYear,"

            val country = filmRequest.production_countries[0].iso_3166_1

            val stringBuilderGenres = StringBuilder()
            for (i in filmRequest.genres.indices) {
                stringBuilderGenres.append(", ").append(filmRequest.genres[i].name)
            }
            val genres = stringBuilderGenres.toString()

            val description = "$year $country$genres"

            onSuccessResponse(
                filmRequest.title,
                filmRequest.original_title,
                filmRequest.vote_average.toString(),
                time,
                description,
                filmRequest.overview
            )
        }
    }

    private fun onSuccessResponse(
        nameReady: String,
        originalTitleReady: String,
        ratingReady: String,
        timeReady: String,
        descriptionReady: String,
        overviewReady: String
    ) {
        putLoadResult(DETAILS_RESPONSE_SUCCESS_EXTRA)
        broadcastIntent.putExtra(DETAILS_TITLE_EXTRA, nameReady)
        broadcastIntent.putExtra(DETAILS_ORIGINAL_TITLE_EXTRA, originalTitleReady)
        broadcastIntent.putExtra(DETAILS_VOTE_AVERAGE_EXTRA, ratingReady)
        broadcastIntent.putExtra(DETAILS_RUNTIME_EXTRA, timeReady)
        broadcastIntent.putExtra(DETAILS_DESCRIPTION_EXTRA, descriptionReady)
        broadcastIntent.putExtra(DETAILS_OVERVIEW_EXTRA, overviewReady)

        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
    }

    private fun onMalformedURL() {
        putLoadResult(DETAILS_URL_MALFORMED_EXTRA)
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
    }

    private fun onErrorRequest(error: String) {
        putLoadResult(DETAILS_REQUEST_ERROR_EXTRA)
        broadcastIntent.putExtra(DETAILS_REQUEST_ERROR_MESSAGE_EXTRA, error)
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
    }

    private fun onEmptyIntent() {
        putLoadResult(DETAILS_INTENT_EMPTY_EXTRA)
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
    }

    private fun onEmptyData() {
        putLoadResult(DETAILS_DATA_EMPTY_EXTRA)
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
    }

    private fun putLoadResult(result: String) {
        broadcastIntent.putExtra(DETAILS_LOAD_RESULT_EXTRA, result)
    }
}
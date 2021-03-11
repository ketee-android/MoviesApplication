package com.ketee_jishs.moviesapplication.main_recyclers

import com.google.gson.GsonBuilder
import com.ketee_jishs.moviesapplication.BuildConfig
import com.ketee_jishs.moviesapplication.movie_data.RecyclerMoviesDTO
import com.ketee_jishs.moviesapplication.repository.PopularWeekApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class RemoteRecyclerWeekDataSource {
    private val popularWeekApi = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .client(createOkHttpClient(PopularWeekApiInterceptor()))
        .build().create(PopularWeekApi::class.java)

    fun getPopularWeekDetails(callback: Callback<RecyclerMoviesDTO>) {
        popularWeekApi.getPopularWeekMovies(BuildConfig.MOVIES_API_KEY, "ru").enqueue(callback)
    }

    private fun createOkHttpClient(interceptor: Interceptor) : OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return httpClient.build()
    }

    inner class PopularWeekApiInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            return chain.proceed(chain.request())
        }
    }
}
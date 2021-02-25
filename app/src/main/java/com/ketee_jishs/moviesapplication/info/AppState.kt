package com.ketee_jishs.moviesapplication.info

sealed class AppState {
    data class Success(val filmData: List<InfoList>) : AppState()
    data class Error(val error: Throwable) : AppState()
}
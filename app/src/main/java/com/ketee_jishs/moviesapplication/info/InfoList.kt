package com.ketee_jishs.moviesapplication.info

data class InfoList(
    val id: Int,
    val name: String,
    val originalTitle: String,
    val rating: String,
    val time: String,
    val description: String,
    val overview: String,
    val posterPath: String?
)
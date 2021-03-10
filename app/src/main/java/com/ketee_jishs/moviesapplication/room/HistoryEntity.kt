package com.ketee_jishs.moviesapplication.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryEntity (
    @PrimaryKey
    val id: Int,
    val name: String,
    val originalTitle: String,
    val rating: String,
    val time: String,
    val description: String,
    val overview: String,
    val posterPath: String?
)
package com.ketee_jishs.moviesapplication.adapter

import android.net.Uri

data class ItemFilm(
    var filmName: String,
    var id: String,
    var year: String,
    var rating: String,
    var poster: Uri
)
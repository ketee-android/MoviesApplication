package com.ketee_jishs.moviesapplication.adapter

import android.net.Uri
import androidx.databinding.ObservableField

class ItemMovie(
    var filmName: String,
    var id: String,
    var year: String,
    var rating: String,
    var poster: Uri,
    var adult: Boolean
) {
    var visibility = ObservableField<Boolean>(false)

    companion object {
       var isVisible: Boolean = false
    }

    fun setVisibility() {
        visibility.set(isVisible)
    }
}
package com.ketee_jishs.moviesapplication.info

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

@RequiresApi(Build.VERSION_CODES.N)
class InfoViewModel : ViewModel() {
    var filmName = ObservableField<String>()
    var originalTitle = ObservableField<String>()
    var rating = ObservableField<String>()
    var time = ObservableField<String>()
    var description = ObservableField<String>()
    var overview = ObservableField<String>()
    var isVisible = ObservableField(false)

    fun loadResult(
        result: String?,
        titleResult: String?,
        originalTitleResult: String?,
        voteResult: String?,
        runtimeResult: String?,
        descriptionResult: String?,
        overviewResult: String?
    ) {
        when (result) {
            DETAILS_INTENT_EMPTY_EXTRA -> TODO(PROCESS_ERROR)
            DETAILS_DATA_EMPTY_EXTRA -> TODO(PROCESS_ERROR)
            DETAILS_RESPONSE_EMPTY_EXTRA -> TODO(PROCESS_ERROR)
            DETAILS_REQUEST_ERROR_EXTRA -> TODO(PROCESS_ERROR)
            DETAILS_REQUEST_ERROR_MESSAGE_EXTRA -> TODO(PROCESS_ERROR)
            DETAILS_URL_MALFORMED_EXTRA -> TODO(PROCESS_ERROR)
            DETAILS_RESPONSE_SUCCESS_EXTRA -> {
                filmName.set(titleResult)
                originalTitle.set(originalTitleResult)
                rating.set(voteResult)
                time.set(runtimeResult)
                description.set(descriptionResult)
                overview.set(overviewResult)
                isVisible.set(true)
            }
            else -> TODO(PROCESS_ERROR)
        }
    }
}
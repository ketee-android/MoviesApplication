package com.ketee_jishs.moviesapplication.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ketee_jishs.moviesapplication.App.Companion.getHistoryDao
import com.ketee_jishs.moviesapplication.info.AppState
import com.ketee_jishs.moviesapplication.repository.LocalRepository
import com.ketee_jishs.moviesapplication.repository.LocalRepositoryImpl

class HistoryViewModel(
    val historyLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val historyRepository: LocalRepository = LocalRepositoryImpl(getHistoryDao())
) : ViewModel() {

    fun getAllHistory() {
        historyLiveData.value = AppState.Success(historyRepository.getAllHistory())
    }
}
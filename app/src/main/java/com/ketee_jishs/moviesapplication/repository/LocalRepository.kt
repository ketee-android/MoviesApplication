package com.ketee_jishs.moviesapplication.repository

import com.ketee_jishs.moviesapplication.info.InfoList

interface LocalRepository {
    fun getAllHistory(): List<InfoList>
    fun saveEntity(infoList: InfoList)
}
package com.ketee_jishs.moviesapplication.repository

import com.ketee_jishs.moviesapplication.info.InfoList
import com.ketee_jishs.moviesapplication.room.HistoryDao
import com.ketee_jishs.moviesapplication.room.HistoryEntity

class LocalRepositoryImpl(private val localDataSource: HistoryDao) : LocalRepository {
    override fun getAllHistory(): List<InfoList> {
        return convertHistoryEntityToInfo(localDataSource.all())
    }

    override fun saveEntity(infoList: InfoList) {
        localDataSource.insert(convertMovieInfoToEntity(infoList))
    }

    private fun convertHistoryEntityToInfo(entityList: List<HistoryEntity>): List<InfoList> {
        return entityList.map {
            InfoList(
                it.id,
                it.name,
                it.originalTitle,
                it.rating,
                it.time,
                it.description,
                it.overview,
                it.posterPath,
                it.comment
            )
        }
    }

    private fun convertMovieInfoToEntity(infoList: InfoList): HistoryEntity {
        return HistoryEntity(
            infoList.id,
            infoList.name,
            infoList.originalTitle,
            infoList.rating,
            infoList.time,
            infoList.description,
            infoList.overview,
            infoList.posterPath,
            infoList.comment
        )
    }
}
package com.ketee_jishs.moviesapplication.room

import androidx.room.*

@Dao
interface HistoryDao {
    @Query("SELECT * FROM HistoryEntity")
    fun all(): List<HistoryEntity>

    @Query("SELECT * FROM HistoryEntity WHERE id LIKE :id")
    fun getDataById(id: Int): List<HistoryEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: HistoryEntity)

    @Update
    fun update(entity: HistoryEntity)

    @Query ("UPDATE HistoryEntity SET comment = :newComment WHERE id LIKE :id")
    fun updateComment(newComment: String, id: Int)

    @Delete
    fun delete(entity: HistoryEntity)
}
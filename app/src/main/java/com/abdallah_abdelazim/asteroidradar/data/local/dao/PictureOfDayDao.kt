package com.abdallah_abdelazim.asteroidradar.data.local.dao

import androidx.room.*
import com.abdallah_abdelazim.asteroidradar.data.local.entity.PictureOfDayEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PictureOfDayDao {

    @Query("SELECT * FROM PictureOfDay LIMIT 1")
    fun get(): Flow<PictureOfDayEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pictureOfDayDto: PictureOfDayEntity)

    @Delete
    suspend fun delete(pictureOfDayDto: PictureOfDayEntity)

    @Query("DELETE FROM PictureOfDay")
    suspend fun deleteAll()

}
package com.abdallah_abdelazim.asteroidradar.data.local.dao

import androidx.room.*
import com.abdallah_abdelazim.asteroidradar.data.local.entity.AsteroidEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AsteroidDao {

    @Query("SELECT * FROM Asteroid WHERE closeApproachDate >= :today ORDER BY closeApproachDate ASC")
    fun getUpcomingAsteroids(today: String): Flow<List<AsteroidEntity>>

    @Query("SELECT * FROM  Asteroid WHERE closeApproachDate == :today ORDER BY closeApproachDate DESC")
    fun getTodayAsteroids(today: String): Flow<List<AsteroidEntity>>

    @Query("SELECT * FROM Asteroid WHERE closeApproachDate < :today ORDER BY closeApproachDate DESC")
    fun getSavedBeforeTodayAsteroid(today: String): Flow<List<AsteroidEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg asteroids: AsteroidEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(asteroids: List<AsteroidEntity>)

    @Delete
    suspend fun delete(asteroid: AsteroidEntity)

    @Query("DELETE FROM Asteroid")
    suspend fun deleteAll()

}
package com.abdallah_abdelazim.asteroidradar.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.abdallah_abdelazim.asteroidradar.data.local.entity.AsteroidEntity

@Dao
interface AsteroidDao {

    @Query("SELECT * FROM Asteroid")
    fun getAll(): List<AsteroidEntity>

}
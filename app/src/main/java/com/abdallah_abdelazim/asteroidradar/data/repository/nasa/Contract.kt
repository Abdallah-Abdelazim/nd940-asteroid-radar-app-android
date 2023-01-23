package com.abdallah_abdelazim.asteroidradar.data.repository.nasa

import com.abdallah_abdelazim.asteroidradar.data.local.entity.AsteroidEntity
import com.abdallah_abdelazim.asteroidradar.data.local.entity.PictureOfDayEntity
import com.abdallah_abdelazim.asteroidradar.data.remote.model.AsteroidDto
import com.abdallah_abdelazim.asteroidradar.data.remote.model.PictureOfDayDto
import kotlinx.coroutines.flow.Flow

interface NasaRemoteDataSource {

    suspend fun getNearEarthObjects(
        startDate: String,
        endDate: String
    ): List<AsteroidDto>

    suspend fun getNasaPictureOfDay(): PictureOfDayDto
}

interface NasaLocalDataSource {

    fun getUpcomingAsteroids(today: String): Flow<List<AsteroidEntity>>

    fun getTodayAsteroids(today: String): Flow<List<AsteroidEntity>>

    fun getSavedBeforeTodayAsteroids(today: String): Flow<List<AsteroidEntity>>

    suspend fun insertAllAsteroids(asteroids: List<AsteroidEntity>)

    suspend fun deleteAllAsteroids()

    fun getPictureOfDay(): Flow<PictureOfDayEntity>

    suspend fun insertPictureOfDay(pictureOfDayEntity: PictureOfDayEntity)

    suspend fun deleteAllPictureOfDay()
}

interface NasaRepository {

    suspend fun refreshNearEarthObjects(startDate: String, endDate: String)

    suspend fun refreshWeekNearEarthObjects()

    suspend fun refreshNasaPictureOfDay()

    suspend fun refreshAllData(startDate: String, endDate: String)

    suspend fun refreshAllWeekData()

    fun getUpcomingAsteroids(): Flow<List<AsteroidEntity>>

    fun getTodayAsteroids(): Flow<List<AsteroidEntity>>

    fun getSavedBeforeTodayAsteroids(): Flow<List<AsteroidEntity>>

    fun getNasaPictureOfDay(): Flow<PictureOfDayEntity>
}
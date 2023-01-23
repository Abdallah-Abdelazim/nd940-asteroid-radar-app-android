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

    fun getAllAsteroids(): Flow<List<AsteroidEntity>>

    suspend fun insertAllAsteroids(asteroids: List<AsteroidEntity>)

    suspend fun deleteAllAsteroids()

    fun getPictureOfDay(): Flow<PictureOfDayEntity>

    suspend fun insertPictureOfDay(pictureOfDayEntity: PictureOfDayEntity)

    suspend fun deleteAllPictureOfDay()
}

interface NasaRepository {

    suspend fun cacheNearEarthObjects(startDate: String, endDate: String)

    fun getCachedNearEarthObjects(): Flow<List<AsteroidEntity>>

    suspend fun cacheNasaPictureOfDay()

    fun getCachedNasaPictureOfDay(): Flow<PictureOfDayEntity>
}
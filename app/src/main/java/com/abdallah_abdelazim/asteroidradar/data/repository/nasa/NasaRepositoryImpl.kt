package com.abdallah_abdelazim.asteroidradar.data.repository.nasa

import com.abdallah_abdelazim.asteroidradar.data.local.entity.AsteroidEntity
import com.abdallah_abdelazim.asteroidradar.data.local.entity.PictureOfDayEntity
import com.abdallah_abdelazim.asteroidradar.data.mapper.toAsteroidEntity
import com.abdallah_abdelazim.asteroidradar.data.mapper.toPictureOfDayEntity
import kotlinx.coroutines.flow.Flow

class NasaRepositoryImpl(
    private val nasaRemoteDataSource: NasaRemoteDataSource,
    private val nasaLocalDataSource: NasaLocalDataSource
) : NasaRepository {

    override suspend fun cacheNearEarthObjects(
        startDate: String,
        endDate: String
    ) {
        val asteroidsList = nasaRemoteDataSource.getNearEarthObjects(startDate, endDate)
        nasaLocalDataSource.insertAllAsteroids(
            asteroidsList.map { toAsteroidEntity(it) }
        )
    }

    override fun getCachedNearEarthObjects(): Flow<List<AsteroidEntity>> {
        return nasaLocalDataSource.getAllAsteroids()
    }

    override suspend fun cacheNasaPictureOfDay() {
        val picOfDay = nasaRemoteDataSource.getNasaPictureOfDay()
        nasaLocalDataSource.insertPictureOfDay(
            toPictureOfDayEntity(picOfDay)
        )
    }

    override fun getCachedNasaPictureOfDay(): Flow<PictureOfDayEntity> {
        return nasaLocalDataSource.getPictureOfDay()
    }

}

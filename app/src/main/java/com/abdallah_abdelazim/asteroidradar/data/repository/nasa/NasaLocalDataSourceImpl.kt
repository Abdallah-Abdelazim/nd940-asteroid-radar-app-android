package com.abdallah_abdelazim.asteroidradar.data.repository.nasa

import com.abdallah_abdelazim.asteroidradar.data.local.dao.AsteroidDao
import com.abdallah_abdelazim.asteroidradar.data.local.dao.PictureOfDayDao
import com.abdallah_abdelazim.asteroidradar.data.local.entity.AsteroidEntity
import com.abdallah_abdelazim.asteroidradar.data.local.entity.PictureOfDayEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

class NasaLocalDataSourceImpl(
    private val asteroidDao: AsteroidDao,
    private val pictureOfDayDao: PictureOfDayDao
) : NasaLocalDataSource {

    override fun getUpcomingAsteroids(today: String): Flow<List<AsteroidEntity>> {
        return asteroidDao.getUpcomingAsteroids(today).distinctUntilChanged()
    }

    override fun getTodayAsteroids(today: String): Flow<List<AsteroidEntity>> {
        return asteroidDao.getTodayAsteroids(today).distinctUntilChanged()
    }

    override fun getSavedBeforeTodayAsteroids(today: String): Flow<List<AsteroidEntity>> {
        return asteroidDao.getSavedBeforeTodayAsteroid(today).distinctUntilChanged()
    }

    override suspend fun insertAllAsteroids(asteroids: List<AsteroidEntity>) {
        asteroidDao.insertAll(asteroids)
    }

    override suspend fun deleteAllAsteroids() {
        asteroidDao.deleteAll()
    }

    override fun getPictureOfDay(): Flow<PictureOfDayEntity> {
        return pictureOfDayDao.get().distinctUntilChanged()
    }

    override suspend fun insertPictureOfDay(pictureOfDayEntity: PictureOfDayEntity) {
        pictureOfDayDao.insert(pictureOfDayEntity)
    }

    override suspend fun deleteAllPictureOfDay() {
        pictureOfDayDao.deleteAll()
    }

}
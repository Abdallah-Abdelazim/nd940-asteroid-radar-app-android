package com.abdallah_abdelazim.asteroidradar.data.repository.nasa

import android.util.Log
import com.abdallah_abdelazim.asteroidradar.data.Constants
import com.abdallah_abdelazim.asteroidradar.data.local.entity.AsteroidEntity
import com.abdallah_abdelazim.asteroidradar.data.local.entity.PictureOfDayEntity
import com.abdallah_abdelazim.asteroidradar.data.mapper.toAsteroidEntity
import com.abdallah_abdelazim.asteroidradar.data.mapper.toPictureOfDayEntity
import kotlinx.coroutines.flow.Flow
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class NasaRepositoryImpl(
    private val nasaRemoteDataSource: NasaRemoteDataSource,
    private val nasaLocalDataSource: NasaLocalDataSource
) : NasaRepository {

    override suspend fun refreshNearEarthObjects(
        startDate: String,
        endDate: String
    ) {
        try {
            val asteroidsList = nasaRemoteDataSource.getNearEarthObjects(startDate, endDate)
            nasaLocalDataSource.deleteAllAsteroids()
            nasaLocalDataSource.insertAllAsteroids(
                asteroidsList.map { toAsteroidEntity(it) }
            )
        } catch (e: Exception) {
            Log.e(TAG, "Exception", e)
        }
    }

    override suspend fun refreshWeekNearEarthObjects() {
        val dateFormat: DateFormat =
            SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())

        val todayDateStr = run {
            val todayDate = Date()
            dateFormat.format(todayDate)
        }

        val weekLaterDateStr = run {
            val calendar = Calendar.getInstance()
            calendar.time = Date()
            calendar[Calendar.DAY_OF_MONTH] = calendar[Calendar.DAY_OF_MONTH] + 7
            val weekendDate = calendar.time
            dateFormat.format(weekendDate)
        }

        refreshNearEarthObjects(todayDateStr, weekLaterDateStr)
    }

    override suspend fun refreshNasaPictureOfDay() {
        try {
            val picOfDay = nasaRemoteDataSource.getNasaPictureOfDay()
            nasaLocalDataSource.deleteAllPictureOfDay()
            nasaLocalDataSource.insertPictureOfDay(
                toPictureOfDayEntity(picOfDay)
            )
        } catch (e: Exception) {
            Log.e(TAG, "Exception", e)
        }
    }

    override suspend fun refreshAllData(
        startDate: String,
        endDate: String
    ) {
        refreshNearEarthObjects(startDate, endDate)
        refreshNasaPictureOfDay()
    }

    override suspend fun refreshAllWeekData() {
        refreshWeekNearEarthObjects()
        refreshNasaPictureOfDay()
    }

    override fun getUpcomingAsteroids(): Flow<List<AsteroidEntity>> {
        val dateFormat: DateFormat =
            SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())

        val todayDateStr = run {
            val todayDate = Date()
            dateFormat.format(todayDate)
        }

        return nasaLocalDataSource.getUpcomingAsteroids(todayDateStr)
    }

    override fun getTodayAsteroids(): Flow<List<AsteroidEntity>> {
        val dateFormat: DateFormat =
            SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())

        val todayDateStr = run {
            val todayDate = Date()
            dateFormat.format(todayDate)
        }

        return nasaLocalDataSource.getTodayAsteroids(todayDateStr)
    }

    override fun getSavedBeforeTodayAsteroids(): Flow<List<AsteroidEntity>> {
        val dateFormat: DateFormat =
            SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())

        val todayDateStr = run {
            val todayDate = Date()
            dateFormat.format(todayDate)
        }

        return nasaLocalDataSource.getSavedBeforeTodayAsteroids(todayDateStr)
    }

    override fun getNasaPictureOfDay(): Flow<PictureOfDayEntity> {
        return nasaLocalDataSource.getPictureOfDay()
    }

    companion object {

        private val TAG = NasaRepositoryImpl::class.simpleName

    }
}

package com.abdallah_abdelazim.asteroidradar.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abdallah_abdelazim.asteroidradar.data.local.dao.AsteroidDao
import com.abdallah_abdelazim.asteroidradar.data.local.dao.PictureOfDayDao
import com.abdallah_abdelazim.asteroidradar.data.local.entity.AsteroidEntity
import com.abdallah_abdelazim.asteroidradar.data.local.entity.PictureOfDayEntity

@Database(
    entities = [
        AsteroidEntity::class,
        PictureOfDayEntity::class
    ], version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun asteroidDao(): AsteroidDao

    abstract fun pictureOfDayDao(): PictureOfDayDao

    companion object {

        const val DB_NAME = "asteroid_radar.db"

    }
}
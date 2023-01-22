package com.abdallah_abdelazim.asteroidradar.data.local.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.abdallah_abdelazim.asteroidradar.AsteroidRadarApplication
import com.abdallah_abdelazim.asteroidradar.data.local.dao.AsteroidDao
import com.abdallah_abdelazim.asteroidradar.data.local.entity.AsteroidEntity

@Database(entities = [AsteroidEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun asteroidDao(): AsteroidDao

    companion object {

        private const val DB_NAME = "asteroids.db"

        private var _instance: AppDatabase? = null
        val instance: AppDatabase
            get() = _instance ?: synchronized(this) {
                if (_instance == null) {
                    _instance = Room.databaseBuilder(
                        AsteroidRadarApplication.appContext,
                        AppDatabase::class.java, DB_NAME
                    ).build()
                }
                _instance!!
            }

    }
}
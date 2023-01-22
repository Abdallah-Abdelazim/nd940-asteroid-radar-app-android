package com.abdallah_abdelazim.asteroidradar

import android.app.Application
import android.content.Context

class AsteroidRadarApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        appContext = this
    }


    companion object {

        lateinit var appContext: Context

    }
}
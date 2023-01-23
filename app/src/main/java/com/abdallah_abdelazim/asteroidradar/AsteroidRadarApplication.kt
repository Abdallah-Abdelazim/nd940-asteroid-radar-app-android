package com.abdallah_abdelazim.asteroidradar

import android.app.Application
import com.abdallah_abdelazim.asteroidradar.data.di.dataModule
import com.abdallah_abdelazim.asteroidradar.ui.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class AsteroidRadarApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        setupKoinDi()
    }

    private fun setupKoinDi() {
        startKoin {
            androidLogger()
            androidContext(this@AsteroidRadarApplication)
            modules(
                dataModule,
                uiModule
            )
        }
    }
}
package com.abdallah_abdelazim.asteroidradar

import android.app.Application
import androidx.work.*
import com.abdallah_abdelazim.asteroidradar.data.di.dataModule
import com.abdallah_abdelazim.asteroidradar.data.worker.RefreshDataWorker
import com.abdallah_abdelazim.asteroidradar.ui.di.uiModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import java.util.concurrent.TimeUnit

class AsteroidRadarApplication : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()

        setupKoinDi()
        initRefreshDataWorker()
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

    private fun initRefreshDataWorker() {
        applicationScope.launch {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresCharging(true)
                .setRequiresBatteryNotLow(true)
                .build()

            val repeatingRequest = PeriodicWorkRequestBuilder<RefreshDataWorker>(1, TimeUnit.DAYS)
                .setConstraints(constraints)
                .build()

            WorkManager.getInstance(this@AsteroidRadarApplication).enqueueUniquePeriodicWork(
                RefreshDataWorker.WORKER_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                repeatingRequest
            )
        }
    }
}
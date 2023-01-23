package com.abdallah_abdelazim.asteroidradar.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.abdallah_abdelazim.asteroidradar.data.repository.nasa.NasaRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RefreshDataWorker(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params), KoinComponent {

    private val nasaRepository: NasaRepository by inject()

    override suspend fun doWork(): Result {
        return try {
            nasaRepository.refreshAllWeekData()
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

    companion object {
        const val WORKER_NAME = "refresh_data"
    }
}
package com.abdallah_abdelazim.asteroidradar.data.repository.nasa

import com.abdallah_abdelazim.asteroidradar.data.remote.api.NasaApi
import com.abdallah_abdelazim.asteroidradar.data.remote.exception.UnsuccessfulNetworkResponseException
import com.abdallah_abdelazim.asteroidradar.data.remote.model.AsteroidDto
import com.abdallah_abdelazim.asteroidradar.data.remote.model.PictureOfDayDto
import com.abdallah_abdelazim.asteroidradar.data.remote.utitls.handleResponse

class NasaRemoteDataSourceImpl(
    private val nasaApi: NasaApi
) : NasaRemoteDataSource {

    @Throws(
        UnsuccessfulNetworkResponseException::class,
        IllegalStateException::class
    )
    override suspend fun getNearEarthObjects(
        startDate: String,
        endDate: String
    ): List<AsteroidDto> {
        return handleResponse(
            nasaApi.nearEarthObjectsFeed(startDate, endDate)
        ) {
            nearEarthObjects.values.flatten()
        }
    }

    override suspend fun getNasaPictureOfDay(): PictureOfDayDto {
        val pictureOfDayDto = handleResponse(
            nasaApi.pictureOfDay()
        ) { this }
        return if (pictureOfDayDto.mediaType == "image") pictureOfDayDto
        else getNasaPictureOfDay() // Retry
    }

}
package com.abdallah_abdelazim.asteroidradar.data.remote.api

import com.abdallah_abdelazim.asteroidradar.data.Constants
import com.abdallah_abdelazim.asteroidradar.data.remote.model.NearEarthObjectsFeedResponse
import com.abdallah_abdelazim.asteroidradar.data.remote.model.PictureOfDayDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApi {

    @GET("neo/rest/v1/feed")
    suspend fun nearEarthObjectsFeed(
        @Query("start_date")
        startDate: String,
        @Query("end_date")
        endDate: String,
        @Query("api_key")
        apiKey: String = Constants.NASA_API_KEY
    ): Response<NearEarthObjectsFeedResponse>

    @GET("planetary/apod")
    suspend fun pictureOfDay(
        @Query("api_key")
        apiKey: String = Constants.NASA_API_KEY
    ): Response<PictureOfDayDto>

}
package com.abdallah_abdelazim.asteroidradar.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PictureOfDayDto(
    @Json(name = "media_type")
    val mediaType: String,
    val title: String,
    val url: String
)
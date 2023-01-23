package com.abdallah_abdelazim.asteroidradar.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NearEarthObjectsFeedResponse(
    @Json(name = "near_earth_objects")
    val nearEarthObjects: Map<String, List<AsteroidDto>>
)
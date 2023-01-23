package com.abdallah_abdelazim.asteroidradar.data.remote.model

import com.squareup.moshi.Json

data class NearEarthObjectsFeedResponse(
    @Json(name = "near_earth_objects")
    val nearEarthObjects: Map<String, List<AsteroidDto>>
)
package com.abdallah_abdelazim.asteroidradar.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AsteroidDto(
    val id: String,
    val name: String,
    @Json(name = "absolute_magnitude_h")
    val absoluteMagnitude: Double,
    @Json(name = "estimated_diameter")
    val estimatedDiameter: EstimatedDiameter,
    @Json(name = "close_approach_data")
    val closeApproachData: List<CloseApproachData>,
    @Json(name = "is_potentially_hazardous_asteroid")
    val isPotentiallyHazardous: Boolean
)

@JsonClass(generateAdapter = true)
data class CloseApproachData(
    @Json(name = "close_approach_date")
    val date: String,
    @Json(name = "epoch_date_close_approach")
    val epochDate: Long,
    @Json(name = "relative_velocity")
    val relativeVelocity: RelativeVelocity,
    @Json(name = "miss_distance")
    val missDistance: Distance
)

@JsonClass(generateAdapter = true)
data class EstimatedDiameter(
    @Json(name = "kilometers")
    val kilometers: EstimatedDiameterData
)

@JsonClass(generateAdapter = true)
data class EstimatedDiameterData(
    @Json(name = "estimated_diameter_min")
    val min: Double,
    @Json(name = "estimated_diameter_max")
    val max: Double
)

@JsonClass(generateAdapter = true)
data class RelativeVelocity(
    @Json(name = "kilometers_per_second")
    val kmPerSecond: String
)

@JsonClass(generateAdapter = true)
data class Distance(
    val astronomical: String
)
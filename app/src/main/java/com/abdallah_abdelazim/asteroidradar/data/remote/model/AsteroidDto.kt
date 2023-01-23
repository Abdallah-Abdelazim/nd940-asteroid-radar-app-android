package com.abdallah_abdelazim.asteroidradar.data.remote.model

import com.squareup.moshi.Json

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

data class EstimatedDiameter(
    @Json(name = "kilometers")
    val kilometers: EstimatedDiameterData
)

data class EstimatedDiameterData(
    @Json(name = "estimated_diameter_min")
    val min: Double,
    @Json(name = "estimated_diameter_max")
    val max: Double
)

data class RelativeVelocity(
    @Json(name = "kilometers_per_second")
    val kmPerSecond: String
)

data class Distance(
    val astronomical: String
)
package com.abdallah_abdelazim.asteroidradar.data.local.entity

import androidx.room.Entity

@Entity
data class AsteroidEntity(
    val id: Long,
    val name: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameterKm: Double,
    val relativeVelocityKmPerSec: Double,
    val distanceFromEarthAu: Double,
    val isPotentiallyHazardous: Boolean
)
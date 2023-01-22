package com.abdallah_abdelazim.asteroidradar.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "Asteroid"
)
data class AsteroidEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameterKm: Double,
    val relativeVelocityKmPerSec: Double,
    val distanceFromEarthAu: Double,
    val isPotentiallyHazardous: Boolean
)
package com.abdallah_abdelazim.asteroidradar.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AsteroidUiModel(
    val id: String,
    val name: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameterKm: Double,
    val relativeVelocityKmPerSec: Double,
    val distanceFromEarthAu: Double,
    val isPotentiallyHazardous: Boolean
) : Parcelable
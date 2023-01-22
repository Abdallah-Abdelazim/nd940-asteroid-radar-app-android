package com.abdallah_abdelazim.asteroidradar.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AsteroidUiModel(
    val id: Long,
    val name: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameterKm: Double,
    val relativeVelocityKmPerSec: Double,
    val distanceFromEarthAu: Double,
    val isPotentiallyHazardous: Boolean
) : Parcelable
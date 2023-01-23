package com.abdallah_abdelazim.asteroidradar.ui.mapper

import com.abdallah_abdelazim.asteroidradar.data.local.entity.AsteroidEntity
import com.abdallah_abdelazim.asteroidradar.data.local.entity.PictureOfDayEntity
import com.abdallah_abdelazim.asteroidradar.ui.model.AsteroidUiModel
import com.abdallah_abdelazim.asteroidradar.ui.model.PictureOfDayUiModel

fun toAsteroidUiModel(asteroidEntity: AsteroidEntity?): AsteroidUiModel? =
    asteroidEntity?.let {
        AsteroidUiModel(
            it.id,
            it.name,
            it.closeApproachDate,
            it.absoluteMagnitude,
            it.estimatedDiameterKm,
            it.relativeVelocityKmPerSec,
            it.distanceFromEarthAu,
            it.isPotentiallyHazardous
        )
    }

fun toPictureOfDayUiModel(pictureOfDayEntity: PictureOfDayEntity?): PictureOfDayUiModel? =
    pictureOfDayEntity?.let {
        PictureOfDayUiModel(
            it.title,
            it.url
        )
    }
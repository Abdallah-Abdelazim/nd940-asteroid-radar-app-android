package com.abdallah_abdelazim.asteroidradar.data.mapper

import com.abdallah_abdelazim.asteroidradar.data.local.entity.AsteroidEntity
import com.abdallah_abdelazim.asteroidradar.data.local.entity.PictureOfDayEntity
import com.abdallah_abdelazim.asteroidradar.data.remote.model.AsteroidDto
import com.abdallah_abdelazim.asteroidradar.data.remote.model.PictureOfDayDto

fun toAsteroidEntity(asteroidDto: AsteroidDto): AsteroidEntity = asteroidDto.let {
    AsteroidEntity(
        it.id,
        it.name,
        it.closeApproachData.first().date,
        it.absoluteMagnitude,
        it.estimatedDiameter.kilometers.max,
        it.closeApproachData.first().relativeVelocity.kmPerSecond.toDoubleOrNull() ?: 0.0,
        it.closeApproachData.first().missDistance.astronomical.toDoubleOrNull() ?: 0.0,
        it.isPotentiallyHazardous
    )
}

fun toPictureOfDayEntity(pictureOfDayDto: PictureOfDayDto): PictureOfDayEntity =
    pictureOfDayDto.let {
        PictureOfDayEntity(it.title, it.url)
    }
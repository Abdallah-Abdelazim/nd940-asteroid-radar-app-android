package com.abdallah_abdelazim.asteroidradar.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "PictureOfDay"
)
data class PictureOfDayEntity(
    val title: String,
    @PrimaryKey
    val url: String
)
package com.example.myapplication.persistance.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class TrendingEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val firstAirDate: String,
    val originalName: String,
    val backdropPath: String,
    val mediaType: String,
    val posterPath: String
)
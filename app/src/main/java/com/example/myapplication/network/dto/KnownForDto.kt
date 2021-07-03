package com.example.myapplication.network.dto

data class KnownForDto(
    val vote_count: Double,
    val title: String,
    val media_type: String,
    val adult: Boolean,
    val backdrop_path: String,
    val id: Long
)

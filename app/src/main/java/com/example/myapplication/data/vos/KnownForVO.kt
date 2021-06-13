package com.example.myapplication.data.vos

data class KnownForVO(
    val vote_count: Double,
    val title: String,
    val media_type: String,
    val adult: Boolean,
    val backdrop_path: String,
    val id: Long
)
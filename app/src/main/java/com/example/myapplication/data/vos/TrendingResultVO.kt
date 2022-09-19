package com.example.myapplication.data.vos

data class TrendingResultVO(
    val id: Long,
    val name: String,
    val first_air_date: String,
    val original_name: String,
    val backdrop_path: String,
    val media_type: String,
    val poster_path: String
)
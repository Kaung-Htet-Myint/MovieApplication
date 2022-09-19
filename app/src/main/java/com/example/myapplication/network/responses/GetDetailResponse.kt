package com.example.myapplication.network.responses


data class GetDetailResponse(
    val backdrop_path: String,
    val overview: String,
    val original_title: String,
    val original_language: String,
    val popularity: Double,
    val id: Long,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Float,
    val vote_count: Long
)


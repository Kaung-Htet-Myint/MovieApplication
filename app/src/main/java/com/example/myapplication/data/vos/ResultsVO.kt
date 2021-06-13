package com.example.myapplication.data.vos

data class ResultsVO(
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Long,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Float,
    val vote_count: Long,
    val media_type: String,
)
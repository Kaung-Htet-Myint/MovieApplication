package com.example.myapplication.data.vos

import com.example.myapplication.persistance.entities.MovieEntity

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

fun ResultsVO.asEntity(): MovieEntity {
    return MovieEntity(
        id,
        backdrop_path,
        genre_ids,
        original_language,
        original_title,
        overview,
        popularity,
        poster_path,
        release_date,
        title,
        video,
        vote_average,
        vote_count,
        media_type
    )
}
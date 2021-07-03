package com.example.myapplication.network.dto

import com.example.myapplication.domain.MovieDetail

data class MovieDetailDto(
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


fun MovieDetailDto.asDomain(): MovieDetail {
    return MovieDetail(
        backdropPath = backdrop_path,
        overview = overview,
        originalTitle = original_title,
        popularity = popularity,
        id = id,
        posterPath = poster_path,
        releaseDate = release_date,
        originalLanguage = original_language,
        title = title,
        video = video,
        voteAverage = vote_average,
        voteCount = vote_count
    )
}

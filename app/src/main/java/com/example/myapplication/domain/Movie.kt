package com.example.myapplication.domain

import com.example.myapplication.persistance.entities.MovieEntity

data class Movie(
    val backdropPath: String,
    val genreIds: List<Int>,
    val id: Long,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Float,
    val voteCount: Long,
    val isFavorite: Boolean
)

fun Movie.asEntity(movieType: String): MovieEntity {
    return MovieEntity(
        id = movieType+id,
        movieId = id,
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        isFavorite = isFavorite,
        movieType = movieType
    )
}
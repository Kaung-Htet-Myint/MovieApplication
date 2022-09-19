package com.example.myapplication.domain

import com.example.myapplication.persistance.entities.FavEntity

data class MovieDetail(
    val backdropPath: String,
    val overview: String,
    val originalTitle: String,
    val originalLanguage: String,
    val popularity: Double,
    val id: Long,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Float,
    val voteCount: Long
)
fun MovieDetail.asEntity(): FavEntity {
    return FavEntity(
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
    )
}
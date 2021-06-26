package com.example.myapplication.network.dto

import com.example.myapplication.data.vos.Movie
import com.example.myapplication.persistance.entities.MovieEntity

data class MovieDto(
    val backdrop_path: String?,
    val genre_ids: List<Int>?,
    val id: Long?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Float?,
    val vote_count: Long?
)

fun MovieDto.asEntity(movieType: String): MovieEntity {
    return MovieEntity(
        id = movieType+id,
        movieId = id ?: -1,
        backdropPath = backdrop_path.orEmpty(),
        originalLanguage = original_language.orEmpty(),
        originalTitle = original_title.orEmpty(),
        overview = overview.orEmpty(),
        popularity = popularity ?: -1.0,
        posterPath = poster_path.orEmpty(),
        releaseDate = release_date.orEmpty(),
        title = title.orEmpty(),
        video = video ?: false,
        voteAverage = vote_average ?: -1.0f,
        voteCount = vote_count ?: -1,
        movieType = movieType
    )
}

fun MovieDto.asDomain():Movie{
    return Movie(
        backdropPath = backdrop_path.orEmpty(),
        overview = overview.orEmpty(),
        popularity = popularity ?: -1.0,
        title = title.orEmpty(),
        video = video ?: false,
        genreIds = genre_ids.orEmpty(),
        id = id ?: -1,
        originalLanguage = original_language.orEmpty(),
        originalTitle = original_title.orEmpty(),
        posterPath = poster_path.orEmpty(),
        releaseDate = release_date.orEmpty(),
        voteAverage = vote_average ?: -1.0f,
        voteCount = vote_count ?: -1
    )
}
package com.example.myapplication.persistance.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class MovieEntity @JvmOverloads constructor(
    @PrimaryKey
    val id: Long,
    val backdropPath: String,

    @Ignore
    val genreIds: List<Int> = emptyList(),
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
    val movieType: String
)
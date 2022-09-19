package com.example.myapplication.network.dto

import com.example.myapplication.domain.Genres
import com.example.myapplication.domain.MovieGenre

data class MovieGenresDto(
    val genres: List<Genres>
)

fun MovieGenresDto.asDomain(): MovieGenre {
    return MovieGenre(genres)
}
package com.example.myapplication.network.dto

import com.example.myapplication.domain.Tv

data class TvDto(
    val first_air_date: String,
    val id: Long,
    val name: String,
    val poster_path: String,
    val backdrop_path: String,
    val media_type: String
)

fun TvDto.asDomain(): Tv{
    return Tv(
        firstAirDate = first_air_date,
        id = id,
        name = name,
        posterPath = poster_path,
        backdropPath = backdrop_path,
        mediaType = media_type
    )
}
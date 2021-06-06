package com.example.myapplication.network.responses

import com.example.myapplication.data.vos.MovieDetailVO

data class GetDetailResponse(
    val backdrop_path: String,
    val overview: String,
    val original_title: String,
    val original_language: String,
    val popularity: Double
)
    fun GetDetailResponse.asDomain(): MovieDetailVO{
        return MovieDetailVO(backdrop_path,overview,original_title,original_language,popularity)
    }

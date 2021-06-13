package com.example.myapplication.network.responses

import com.example.myapplication.domain.Trending

data class TrendingResponse(
    val page: Int?,
    val results: List<TrendingDto>?
)

data class TrendingDto(
    // person, tv and movie
    val id: Long?,
    val media_type: String?,
    val name: String?,
    // for movie
    val title: String?,

    // person
    val gender: String?,
    val profile_path: String?,

    // tv and movie
    val poster_path: String?,
    val backdrop_path: String?,
    val overview: String?
)





package com.example.myapplication.network.responses

import com.example.myapplication.domain.Genres

data class GetMovieGenreResponse(
    val genres: List<Genres>
)

package com.example.myapplication.network.responses

import com.example.myapplication.network.dto.MovieDto

data class GetMovieResponse(
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
    )
package com.example.myapplication.network.responses

import com.example.myapplication.data.vos.MovieVO
import com.example.myapplication.data.vos.ResultsVO

data class GetMovieResponse(
    val page: Int,
    val results: List<ResultsVO>,
    val total_pages: Int,
    val total_results: Int
    )

fun GetMovieResponse.asDomain(): MovieVO {
    return MovieVO(page,results)
}
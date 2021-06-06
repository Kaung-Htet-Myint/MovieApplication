package com.example.myapplication.network.responses

import com.example.myapplication.data.vos.MovieVO
import com.example.myapplication.data.vos.ResultsVO

data class GetMovieResponse(
    val page: Int,
    val results: List<ResultsVO>
    )

fun GetMovieResponse.asDomain(): MovieVO {
    return MovieVO(page,results)
}
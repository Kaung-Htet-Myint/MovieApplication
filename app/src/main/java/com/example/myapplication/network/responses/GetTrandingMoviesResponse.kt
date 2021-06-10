package com.example.myapplication.network.responses

import com.example.myapplication.data.vos.MovieVO
import com.example.myapplication.data.vos.TrandingMoviesVO
import com.example.myapplication.data.vos.TrandingResultVO

data class GetTrandingMoviesResponse(
    val page: Int,
    val results: List<TrandingResultVO>
)

fun GetTrandingMoviesResponse.asDomain(): TrandingMoviesVO {
    return TrandingMoviesVO(page,results)
}

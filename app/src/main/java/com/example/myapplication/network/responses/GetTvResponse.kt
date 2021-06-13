package com.example.myapplication.network.responses

import com.example.myapplication.data.vos.TrendingTvVO
import com.example.myapplication.data.vos.TvResultsVO

class GetTvResponse(
    val page: Int,
    val results: List<TvResultsVO>
)

fun GetTvResponse.asDomain():TrendingTvVO{
    return TrendingTvVO(page,results)
}
package com.example.myapplication.network.responses

import com.example.myapplication.data.vos.TrendingTvVO
import com.example.myapplication.domain.Tv

class GetTvResponse(
    val page: Int,
    val results: List<Tv>
)

fun GetTvResponse.asDomain():TrendingTvVO{
    return TrendingTvVO(page,results)
}
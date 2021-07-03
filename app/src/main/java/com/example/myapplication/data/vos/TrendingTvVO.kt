package com.example.myapplication.data.vos

import com.example.myapplication.domain.Tv

data class TrendingTvVO(
    val page: Int,
    val results: List<Tv>

)

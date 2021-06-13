package com.example.myapplication.network.responses

import com.example.myapplication.data.vos.PersonResultsVO
import com.example.myapplication.data.vos.TrendingPersonVO

class GetPersonResponse(
    val page: Int,
    val results: List<PersonResultsVO>
)

fun GetPersonResponse.asDomain(): TrendingPersonVO{
    return TrendingPersonVO(page,results)
}
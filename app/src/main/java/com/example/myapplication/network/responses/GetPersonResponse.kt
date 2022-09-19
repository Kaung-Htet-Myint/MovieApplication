package com.example.myapplication.network.responses

import com.example.myapplication.domain.Person
import com.example.myapplication.data.vos.TrendingPersonVO

class GetPersonResponse(
    val page: Int,
    val results: List<Person>
)

fun GetPersonResponse.asDomain(): TrendingPersonVO{
    return TrendingPersonVO(page,results)
}
package com.example.myapplication.network.responses

import com.example.myapplication.data.vos.GenresVO
import com.example.myapplication.data.vos.MovieGenreVO

data class GetMovieGenreResponse(
    val genres: List<GenresVO>
)

fun GetMovieGenreResponse.asDomain(): MovieGenreVO{
    return MovieGenreVO(genres)
}
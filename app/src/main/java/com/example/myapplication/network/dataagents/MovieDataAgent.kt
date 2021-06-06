package com.example.myapplication.network.dataagents

import com.example.myapplication.data.vos.MovieDetailVO
import com.example.myapplication.data.vos.MovieVO

interface MovieDataAgent {
    fun getUpComingMovies(
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMovieDetail(
        id: Long,
        onSuccess: (MovieDetailVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getPopularMoives(
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getTopRatedMovies(
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    )
}
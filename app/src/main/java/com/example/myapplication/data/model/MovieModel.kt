package com.example.myapplication.data.model

import com.example.myapplication.data.vos.MovieDetailVO
import com.example.myapplication.data.vos.MovieVO

interface MovieModel {
    fun getUpcomingMovies(onSuccess: (MovieVO)-> Unit, onFailure: (String)-> Unit)
    fun getMovieDetail(id: Long, onSuccess: (MovieDetailVO) -> Unit, onFailure: (String) -> Unit)
    fun getPopularMovies(onSuccess: (MovieVO) -> Unit, onFailure: (String) -> Unit)
    fun getTopRatedMovies(onSuccess: (MovieVO) -> Unit, onFailure: (String) -> Unit)
}
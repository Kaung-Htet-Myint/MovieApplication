package com.example.myapplication.data.model

import com.example.myapplication.data.vos.MovieDetailVO
import com.example.myapplication.data.vos.MovieVO
import com.example.myapplication.network.dataagents.RetrofitDataAgentImpl

class MovieModelImpl: MovieModel {
    var movieDataAgent = RetrofitDataAgentImpl
    override fun getUpcomingMovies(onSuccess: (MovieVO)-> Unit, onFailure: (String)-> Unit){
        movieDataAgent.getUpComingMovies(onSuccess = {
            onSuccess(it)
        },
        onFailure = {
            onFailure(it)
        })
    }

    override fun getMovieDetail(id: Long, onSuccess: (MovieDetailVO) -> Unit, onFailure: (String) -> Unit) {
        movieDataAgent.getMovieDetail(id, onSuccess = {
            onSuccess(it)
        },
        onFailure = {
            onFailure(it)
        })
    }

    override fun getPopularMovies(onSuccess: (MovieVO) -> Unit, onFailure: (String) -> Unit) {
        movieDataAgent.getPopularMoives(onSuccess = {
            onSuccess(it)
        },
        onFailure = {
            onFailure(it)
        })
    }

    override fun getTopRatedMovies(onSuccess: (MovieVO) -> Unit, onFailure: (String) -> Unit) {
        movieDataAgent.getTopRatedMovies(onSuccess={
            onSuccess(it)
        },
        onFailure = {
            onFailure(it)
        })
    }
}
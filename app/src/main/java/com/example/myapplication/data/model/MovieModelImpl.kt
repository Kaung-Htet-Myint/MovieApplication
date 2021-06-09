package com.example.myapplication.data.model

import androidx.paging.*
import com.example.myapplication.data.vos.MovieDetailVO
import com.example.myapplication.data.vos.MovieVO
import com.example.myapplication.data.vos.ResultsVO
import com.example.myapplication.network.MovieApi
import com.example.myapplication.network.dataagents.RetrofitDataAgentImpl
import com.example.myapplication.pagingsources.MoviesPagingSource
import kotlinx.coroutines.flow.Flow

class MovieModelImpl: MovieModel {
    var movieDataAgent = RetrofitDataAgentImpl
    private var backEnd: MovieApi = RetrofitDataAgentImpl.movieApi
    override fun getUpcomingMovies(
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        movieDataAgent.getUpComingMovies(onSuccess = {
            onSuccess(it)
        },
        onFailure = {
            onFailure(it)
        })
    }

    override fun getMoviesPagingMovies(): Flow<PagingData<ResultsVO>> {
        val flow =  Pager(
            PagingConfig(pageSize = 20)
        ){
            MoviesPagingSource(backEnd)
        }.flow

        return flow
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
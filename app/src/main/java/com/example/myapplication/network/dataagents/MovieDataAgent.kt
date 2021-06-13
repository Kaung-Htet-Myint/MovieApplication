package com.example.myapplication.network.dataagents

import androidx.paging.PagingData
import com.example.myapplication.data.vos.*
import com.example.myapplication.domain.Trending
import com.example.myapplication.network.responses.TrendingDto
import com.example.myapplication.network.responses.TrendingResponse
import kotlinx.coroutines.flow.Flow

interface MovieDataAgent {
    suspend fun getUpComingMovies(): MovieVO

    suspend fun getMovieDetail(id: Long): MovieDetailVO

    suspend fun getPopularMovies(): MovieVO

    suspend fun getTopRatedMovies(): MovieVO

    suspend fun getAllTrending(mediaType: String,timeWindow: String): List<Trending>

    suspend fun getPagingMovies(movieType: String): Flow<PagingData<ResultsVO>>

}
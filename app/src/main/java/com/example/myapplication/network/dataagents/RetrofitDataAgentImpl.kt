package com.example.myapplication.network.dataagents

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.myapplication.ACCESS_TOKEN
import com.example.myapplication.BASE_URL
import com.example.myapplication.data.vos.*
import com.example.myapplication.domain.Trending
import com.example.myapplication.domain.TrendingMapper
import com.example.myapplication.network.MovieApi
import com.example.myapplication.network.responses.*
import com.example.myapplication.pagingsources.MoviesPagingSource
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RetrofitDataAgentImpl @Inject constructor(var movieApi: MovieApi) {

    suspend fun getUpComingMovies(): MovieVO {
        val response = movieApi.getUpcomingMovieResponse(ACCESS_TOKEN)
        return response.asDomain()
    }

    suspend fun getMovieDetail(id: Long): MovieDetailVO {
        val response = movieApi.getMovieDetail(id, ACCESS_TOKEN)
        return response.asDomain()
    }

    suspend fun getPopularMovies(): MovieVO {
        val response = movieApi.getPopularResponse(ACCESS_TOKEN)
        return response.asDomain()
    }

    suspend fun getTopRatedMovies(): MovieVO {
        val response = movieApi.getTopRatedResponse(ACCESS_TOKEN)
        return response.asDomain()
    }

    suspend fun getAllTrending(mediaType: String, timeWindow: String): List<Trending> {
        val response = movieApi.getTrendingResponse(mediaType,timeWindow,ACCESS_TOKEN)
        val trendingMapper = TrendingMapper()

        return trendingMapper.map(response.results)
    }

    fun getPagingMovies(movieType: String): Flow<PagingData<ResultsVO>> {
        val flow =  Pager(
            PagingConfig(pageSize = 20)
        ){
            MoviesPagingSource(movieApi, movieType)
        }.flow

        return flow
    }
}
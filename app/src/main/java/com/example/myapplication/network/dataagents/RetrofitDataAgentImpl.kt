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
import kotlinx.coroutines.flow.Flow
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitDataAgentImpl(context: Context) : MovieDataAgent {
    var movieApi: MovieApi

    init {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(ChuckInterceptor(context))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()

        movieApi = retrofit.create(MovieApi::class.java)
    }

    override suspend fun getUpComingMovies(): MovieVO {
        val response = movieApi.getUpcomingMovieResponse(ACCESS_TOKEN)
        return response.asDomain()
    }

    override suspend fun getMovieDetail(id: Long): MovieDetailVO {
        val response = movieApi.getMovieDetail(id, ACCESS_TOKEN)
        return response.asDomain()
    }

    override suspend fun getPopularMovies(): MovieVO {
        val response = movieApi.getPopularResponse(ACCESS_TOKEN)
        return response.asDomain()
    }

    override suspend fun getTopRatedMovies(): MovieVO {
        val response = movieApi.getTopRatedResponse(ACCESS_TOKEN)
        return response.asDomain()
    }

    override suspend fun getAllTrending(mediaType: String, timeWindow: String): List<Trending> {
        val response = movieApi.getTrendingResponse(mediaType,timeWindow,ACCESS_TOKEN)
        val trendingMapper = TrendingMapper()

        return trendingMapper.map(response.results)
    }

    override suspend fun getPagingMovies(movieType: String): Flow<PagingData<ResultsVO>> {
        val flow =  Pager(
            PagingConfig(pageSize = 20)
        ){
            MoviesPagingSource(movieApi, movieType)
        }.flow

        return flow
    }
}
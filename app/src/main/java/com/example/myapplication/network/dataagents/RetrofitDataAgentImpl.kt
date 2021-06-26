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
import com.example.myapplication.network.dto.MovieDto
import com.example.myapplication.network.dto.asDomain
import com.example.myapplication.network.responses.*
import com.example.myapplication.pagingsources.GenresPagingSource
import com.example.myapplication.pagingsources.MoviesPagingSource
import com.example.myapplication.persistance.entities.MovieEntity
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RetrofitDataAgentImpl @Inject constructor(var movieApi: MovieApi) {

    suspend fun getUpComingMovies(): List<Movie> {
        val response = movieApi.getUpcomingMovieResponse(ACCESS_TOKEN)
        return response.results.map {
            it.asDomain()
        }
    }

    suspend fun getSearchMovies(query: String): List<Movie> {
        val response = movieApi.getSearchResponse(ACCESS_TOKEN,query)
        return response.results.map {
            it.asDomain()
        }
    }

    suspend fun getMovieGenre():MovieGenreVO{
        val response = movieApi.getMovieGenreResponse(ACCESS_TOKEN)
        return response.asDomain()
    }

    suspend fun getMovieDetail(id: Long): MovieDetailVO {
        val response = movieApi.getMovieDetail(id, ACCESS_TOKEN)
        return response.asDomain()
    }

    suspend fun getPopularMovies(): List<Movie> {
        val response = movieApi.getPopularResponse(ACCESS_TOKEN)
        return response.results.map {
            it.asDomain()
        }
    }

    suspend fun getTopRatedMovies(): List<Movie> {
        val response = movieApi.getTopRatedResponse(ACCESS_TOKEN)
        return response.results.map {
            it.asDomain()
        }
    }

    suspend fun getAllTrending(mediaType: String, timeWindow: String): List<Trending> {
        val response = movieApi.getTrendingResponse(mediaType,timeWindow,ACCESS_TOKEN)
        val trendingMapper = TrendingMapper()

        return trendingMapper.map(response.results)
    }

    fun getPagingMovies(movieType: String): Flow<PagingData<Movie>> {
        val flow =  Pager(
            PagingConfig(pageSize = 20)
        ){
            MoviesPagingSource(movieApi, movieType)
        }.flow

        return flow
    }

    fun getGenreMovies(genrId: Int): Flow<PagingData<Movie>>{
        val flow =  Pager(
            PagingConfig(pageSize = 20)
        ){
            GenresPagingSource(movieApi,genrId)
        }.flow

        return flow
    }
}
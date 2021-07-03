package com.example.myapplication.network.dataagents

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.myapplication.ACCESS_TOKEN
import com.example.myapplication.SESSION_ID
import com.example.myapplication.domain.*
import com.example.myapplication.network.FavoritesRequest
import com.example.myapplication.network.MovieApi
import com.example.myapplication.network.dto.asDomain
import com.example.myapplication.paging.GenresPagingSource
import com.example.myapplication.paging.MoviesPagingSource
import kotlinx.coroutines.flow.Flow
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

    suspend fun getMovieGenre(): MovieGenre {
        val response = movieApi.getMovieGenreResponse(ACCESS_TOKEN)
        return response.asDomain()
    }

    suspend fun getMovieDetail(id: Long): MovieDetail {
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

    suspend fun postFavorites(mediaType: String,mediaId: Long, favorite: Boolean):Boolean{
        val response = movieApi.postFavorites(ACCESS_TOKEN, SESSION_ID, FavoritesRequest(mediaType,mediaId,favorite))
        return response.success
    }

    suspend fun postFavoriteMovies(movieId: Long, favorite: Boolean):Boolean{
        return postFavorites("movie",movieId,favorite)
    }

    suspend fun postFavoriteTv(tvId: Long, favorite: Boolean):Boolean{
        return postFavorites("tv",tvId,favorite)
    }

    suspend fun getFavoritedMovies(movieId: Long): Boolean {
        val response = movieApi.getFavoritedMovies(movieId, ACCESS_TOKEN,SESSION_ID)
        return response.favorite
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
        val flow = Pager(
            PagingConfig(pageSize = 20)
        ){
            GenresPagingSource(movieApi,genrId)
        }.flow

        return flow
    }
}
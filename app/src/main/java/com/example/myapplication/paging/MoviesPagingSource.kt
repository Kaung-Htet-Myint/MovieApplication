package com.example.myapplication.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapplication.ACCESS_TOKEN
import com.example.myapplication.SESSION_ID
import com.example.myapplication.domain.Movie
import com.example.myapplication.network.MovieApi
import com.example.myapplication.network.dto.asDomain
import retrofit2.HttpException
import java.io.IOException
import kotlin.Exception

class MoviesPagingSource(val backEnd: MovieApi, var movieType : String) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        try {
            val currentPageNumber = params.key ?: 1

                val response = if (movieType == "upComing")
                                    backEnd.getUpcomingMoviePagingResponse(ACCESS_TOKEN, currentPageNumber)
                                else if (movieType == "popular")
                                    backEnd.getPopularMoviePagingResponse(ACCESS_TOKEN, currentPageNumber)
                                else if (movieType == "topRated")
                                    backEnd.getTopRatedMoviePagingResponse(ACCESS_TOKEN,currentPageNumber)
                                else
                                    throw Exception("invalid movie type")



            return LoadResult.Page(
                data =  response.body()!!.results.map { it.asDomain() },
                prevKey = if (currentPageNumber == 1) null else currentPageNumber.minus(1),
                nextKey = if (currentPageNumber < response.body()!!.total_pages) currentPageNumber.plus(1) else null
            )
        }catch (e :IOException){
            return LoadResult.Error(e)
        }catch (e: HttpException){
            return LoadResult.Error(e)
        }
    }

    suspend fun loadFavorites(params: LoadParams<Int>): LoadResult<Int, Movie>{
        try {
            val currentPageNumber = params.key ?: 1

            val favResponse = backEnd.getFavMovies(ACCESS_TOKEN, SESSION_ID,currentPageNumber)

            return LoadResult.Page(
                data =  favResponse.body()!!.results.map { it.asDomain() },
                prevKey = if (currentPageNumber == 1) null else currentPageNumber.minus(1),
                nextKey = if (currentPageNumber < favResponse.body()!!.total_pages) currentPageNumber.plus(1) else null
            )
        }catch (e: IOException){
            return LoadResult.Error(e)
        }catch (e: HttpException){
            return LoadResult.Error(e)
        }

    }
}

class FavoritesPagingSource(val backEnd: MovieApi) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        try {
            val currentPageNumber = params.key ?: 1

            val favResponse = backEnd.getFavMovies(ACCESS_TOKEN, SESSION_ID,currentPageNumber)

            return LoadResult.Page(
                data =  favResponse.body()!!.results.map { it.asDomain() },
                prevKey = if (currentPageNumber == 1) null else currentPageNumber.minus(1),
                nextKey = if (currentPageNumber < favResponse.body()!!.total_pages) currentPageNumber.plus(1) else null
            )
        }catch (e: IOException){
            return LoadResult.Error(e)
        }catch (e: HttpException){
            return LoadResult.Error(e)
        }
    }
}

class GenresPagingSource(val backEnd: MovieApi, val genre: Int) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        try {
            val currentPageNumber = params.key ?: 1

            val response = backEnd.getDiscoverResponse(ACCESS_TOKEN,currentPageNumber, genre)

            return LoadResult.Page(
                data =  response.body()!!.results.map { it.asDomain() },
                prevKey = if (currentPageNumber == 1) null else currentPageNumber.minus(1),
                nextKey = if (currentPageNumber < response.body()!!.total_pages) currentPageNumber.plus(1) else null
            )
        }catch (e :IOException){
            return LoadResult.Error(e)
        }catch (e: HttpException){
            return LoadResult.Error(e)
        }
    }
}
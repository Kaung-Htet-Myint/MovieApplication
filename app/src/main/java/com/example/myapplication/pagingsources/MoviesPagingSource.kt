package com.example.myapplication.pagingsources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapplication.ACCESS_TOKEN
import com.example.myapplication.data.vos.ResultsVO
import com.example.myapplication.data.vos.asEntity
import com.example.myapplication.network.MovieApi
import com.example.myapplication.persistance.entities.MovieEntity
import retrofit2.HttpException
import java.io.IOException
import kotlin.Exception

class MoviesPagingSource(val backEnd: MovieApi,val movieType: String) : PagingSource<Int, MovieEntity>() {

    override fun getRefreshKey(state: PagingState<Int, MovieEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieEntity> {
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
                data =  response.body()!!.results.map { it.asEntity(movieType) },
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
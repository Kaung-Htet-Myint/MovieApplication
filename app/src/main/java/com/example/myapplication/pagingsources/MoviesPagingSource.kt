package com.example.myapplication.pagingsources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapplication.ACCESS_TOKEN
import com.example.myapplication.data.vos.ResultsVO
import com.example.myapplication.network.MovieApi
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

class MoviesPagingSource(val backEnd: MovieApi) : PagingSource<Int, ResultsVO>() {

    override fun getRefreshKey(state: PagingState<Int, ResultsVO>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultsVO> {
        try {
            val currentPageNumber = params.key ?: 1
                val response = backEnd.getUpcomingMoviePagingResponse(ACCESS_TOKEN, currentPageNumber)

            return LoadResult.Page(
                data =  response.body()!!.results,
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
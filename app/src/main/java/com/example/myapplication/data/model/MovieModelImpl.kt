package com.example.myapplication.data.model

import android.content.Context
import androidx.paging.*
import com.example.myapplication.data.vos.*
import com.example.myapplication.domain.Trending
import com.example.myapplication.network.MovieApi
import com.example.myapplication.network.dataagents.RetrofitDataAgentImpl
import com.example.myapplication.pagingsources.MoviesPagingSource
import kotlinx.coroutines.flow.Flow

class MovieModelImpl(context: Context): MovieModel {
    var movieDataAgent = RetrofitDataAgentImpl(context)
    private var backEnd: MovieApi = RetrofitDataAgentImpl(context).movieApi

    /*override fun getMoviesPagingMovies(): Flow<PagingData<ResultsVO>> {
        val flow =  Pager(
            PagingConfig(pageSize = 20)
        ){
            MoviesPagingSource(backEnd)
        }.flow

        return flow
    }*/
}
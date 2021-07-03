package com.example.myapplication.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import com.example.myapplication.network.MovieApi
import com.example.myapplication.network.dataagents.RetrofitDataAgentImpl
import com.example.myapplication.paging.RemoteMediator
import com.example.myapplication.persistance.AppDatabase
import com.example.myapplication.persistance.entities.asDomain
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavMoviesRepository @Inject constructor(val database: AppDatabase,
                                              val movieApi: MovieApi,
                                              val retrofitDataAgentImpl: RetrofitDataAgentImpl)  {
    @OptIn(ExperimentalPagingApi::class)
    fun postOfFavMovies(pageSize: Int) = Pager(
        config = PagingConfig(pageSize),
        remoteMediator = RemoteMediator(database, movieApi)
    ) {
        database.favDao().pagingSource()
    }.flow.map {
        it.map {
            it.asDomain()
        }
    }
}
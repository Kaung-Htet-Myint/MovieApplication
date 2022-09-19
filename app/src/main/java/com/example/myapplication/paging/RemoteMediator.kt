package com.example.myapplication.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.myapplication.ACCESS_TOKEN
import com.example.myapplication.SESSION_ID
import com.example.myapplication.domain.Movie
import com.example.myapplication.network.MovieApi
import com.example.myapplication.network.dto.asEntity
import com.example.myapplication.persistance.AppDatabase
import com.example.myapplication.persistance.entities.FavEntity

@OptIn(ExperimentalPagingApi::class)
class RemoteMediator(
    private val database: AppDatabase,
    private val networkService: MovieApi
):RemoteMediator<Int,FavEntity>() {
    private val favDao = database.favDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, FavEntity>
    ): MediatorResult {
            return try {
                val loadKey = when(loadType){
                    LoadType.REFRESH -> 1
                    LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                    LoadType.APPEND -> {
                        val lastItem = state.lastItemOrNull()
                        if (lastItem == null) {
                            return MediatorResult.Success(
                                endOfPaginationReached = true
                            )
                        }
                        state.pages.last().nextKey
                    }
                }

                    val response = loadKey?.let {
                        networkService.getFavMovies(
                            ACCESS_TOKEN, SESSION_ID, it
                        )
                    }

                database.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                            favDao.clearAll()
                    }
                    response?.body()?.results?.let {
                        favDao.insertFavMovie(it.map {
                            it.asEntity()
                        })
                    }

                }

                MediatorResult.Success(
                    endOfPaginationReached = false
                )
            }catch (e: Exception){
                MediatorResult.Error(e)
            }catch (e: Exception){
                MediatorResult.Error(e)
            }
    }
}
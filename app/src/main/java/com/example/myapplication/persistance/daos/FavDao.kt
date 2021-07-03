package com.example.myapplication.persistance.daos

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.persistance.entities.FavEntity

@Dao
interface FavDao {
    @Insert
    suspend fun insertFavMovie(favEntity: List<FavEntity>)

    @Query("SELECT * FROM fav_movies")
    fun pagingSource(): PagingSource<Int, FavEntity>

    @Query("SELECT * FROM fav_movies")
    fun getFavMovies():LiveData<List<FavEntity>>

    @Query("DELETE FROM fav_movies")
    suspend fun clearAll()
}
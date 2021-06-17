package com.example.myapplication.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.persistance.entities.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM MovieEntity WHERE movieType = :type")
    fun getUpComingMovies(type: String): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM MovieEntity WHERE movieType = :type")
    fun getPopularMovies(type: String): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM MovieEntity WHERE movieType= :type")
    fun getTopRatedMovies(type: String): LiveData<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllMovies(moviesEntity: List<MovieEntity>)
}
package com.example.myapplication.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.persistance.entities.MovieEntity
import com.example.myapplication.persistance.entities.MoviesWithGenre

@Dao
interface MovieDao {
    @Transaction
    @Query("SELECT * FROM MovieEntity WHERE movieType = :type")
    fun getMovies(type: String): LiveData<List<MoviesWithGenre>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(moviesEntity: List<MovieEntity>)
}
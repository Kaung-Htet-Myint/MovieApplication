package com.example.myapplication.persistance.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.persistance.entities.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM MovieEntity")
    fun getMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllMovies(moviesEntity: List<MovieEntity>)
}
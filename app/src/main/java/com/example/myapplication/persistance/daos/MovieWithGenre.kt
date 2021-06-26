package com.example.myapplication.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.myapplication.persistance.entities.GenreWithMovies
import com.example.myapplication.persistance.entities.MovieGenreEntity
import com.example.myapplication.persistance.entities.MoviesWithGenre

@Dao
interface MovieWithGenre {

    @Insert
    fun insert(movieWithGenre: List<MovieGenreEntity>)

    @Transaction
    @Query("SELECT * FROM GenreEntity WHERE genreId = :genreId")
    fun getMovies(genreId: Int): LiveData<List<GenreWithMovies>>
}
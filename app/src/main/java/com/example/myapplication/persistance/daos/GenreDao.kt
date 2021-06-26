package com.example.myapplication.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.persistance.entities.GenreEntity
import com.example.myapplication.persistance.entities.GenreWithMovies
import com.example.myapplication.persistance.entities.MoviesWithGenre

@Dao
interface GenreDao {

    @Query("SELECT * FROM GenreEntity WHERE genreId = :genreId")
    fun getGenre(genreId: Int):LiveData<GenreWithMovies>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(genreEntity: List<GenreEntity>)
}
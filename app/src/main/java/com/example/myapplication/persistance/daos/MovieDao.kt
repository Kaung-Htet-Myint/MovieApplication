package com.example.myapplication.persistance.daos
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.persistance.entities.MovieEntity
import com.example.myapplication.persistance.entities.MoviesWithGenre
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Transaction
    @Query("SELECT * FROM movie_entity WHERE movieType = :type")
    fun getMovies(type: String): LiveData<List<MoviesWithGenre>>

    @Transaction
    @Query("SELECT * FROM movie_entity WHERE movieId = :id")
    fun getMovieDetails(id: Long): Flow<MoviesWithGenre>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(moviesEntity: List<MovieEntity>)

    @Query("UPDATE movie_entity set isFavorite = :isFav where movieId = :id")
    suspend fun updateMovies(id: Long, isFav: Boolean)

    @Query("DELETE FROM movie_entity")
    suspend fun clearAll()
}
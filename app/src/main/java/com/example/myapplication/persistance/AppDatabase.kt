package com.example.myapplication.persistance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.persistance.daos.GenreDao
import com.example.myapplication.persistance.daos.MovieDao
import com.example.myapplication.persistance.daos.MovieWithGenre
import com.example.myapplication.persistance.daos.TrendingDao
import com.example.myapplication.persistance.entities.*

@Database(entities = arrayOf(MovieEntity::class, TrendingEntity::class, GenreEntity::class, MovieGenreEntity::class), version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun trenidngDao(): TrendingDao
    abstract fun genreDao(): GenreDao
    abstract fun movieWithGenre(): MovieWithGenre
    companion object {
        fun create(applicationContext: Context): AppDatabase {
            return Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "AppDatabase"
            ).build()
        }
    }
}



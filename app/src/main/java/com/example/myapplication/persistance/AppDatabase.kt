package com.example.myapplication.persistance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.persistance.daos.MovieDao
import com.example.myapplication.persistance.daos.TrendingDao
import com.example.myapplication.persistance.entities.MovieEntity
import com.example.myapplication.persistance.entities.TrendingEntity

@Database(entities = arrayOf(MovieEntity::class, TrendingEntity::class), version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun trenidngDao(): TrendingDao

    companion object {
        fun create(applicationContext: Context): AppDatabase {
            return Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
            ).build()
        }
    }
}



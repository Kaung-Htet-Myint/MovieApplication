package com.example.myapplication.persistance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.persistance.daos.MovieDao
import com.example.myapplication.persistance.daos.TrendingDao
import com.example.myapplication.persistance.entities.MovieEntity
import com.example.myapplication.persistance.entities.TrendingEntity

@Database(entities = arrayOf(MovieEntity::class, TrendingEntity::class), version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun trenidngDao(): TrendingDao

    companion object {
        private var appDbInstance: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase{
            if (appDbInstance == null){
                appDbInstance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java, "AppDatabase"
                ).build()
            }

            return appDbInstance!!
        }
    }
}



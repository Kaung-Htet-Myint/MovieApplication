package com.example.myapplication.persistance.daos

import androidx.room.Dao
import androidx.room.Insert
import com.example.myapplication.persistance.entities.TrendingEntity

@Dao
interface TrendingDao {
    @Insert
    fun insertAllTrending(trendingEntity: TrendingEntity)
}
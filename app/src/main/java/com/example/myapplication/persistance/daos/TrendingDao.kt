package com.example.myapplication.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.persistance.entities.TrendingEntity

@Dao
interface TrendingDao {
    @Insert
    fun insertAllTrending(trendingEntity: TrendingEntity)

    @Query("SELECT * FROM TrendingEntity WHERE mediaType = :type ")
    fun getTrendingMovie(type: String): LiveData<List<TrendingEntity>>
}
package com.example.myapplication.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.Trending
import com.example.myapplication.network.dataagents.RetrofitDataAgentImpl
import com.example.myapplication.network.responses.TrendingResponse
import kotlinx.coroutines.launch

class TrendingViewModel(private val app: Application) : AndroidViewModel(app){
    val trendingMovieLiveData : MutableLiveData<List<Trending>> =  MutableLiveData()
    val trendingTvLiveData : MutableLiveData<List<Trending>> =  MutableLiveData()
    val trendingPersonLiveData : MutableLiveData<List<Trending>> =  MutableLiveData()

    fun loadTrending(mediaType: String, timeWindow: String){
        val retrofitDataAgentImpl = RetrofitDataAgentImpl(app)
        viewModelScope.launch {
            when(mediaType){
                "movie" -> trendingMovieLiveData.value = retrofitDataAgentImpl.getAllTrending(mediaType, timeWindow)
                "tv"    ->  trendingTvLiveData.value = retrofitDataAgentImpl.getAllTrending(mediaType, timeWindow)
                else   ->  trendingPersonLiveData.value = retrofitDataAgentImpl.getAllTrending(mediaType, timeWindow)
            }

        }
    }
}
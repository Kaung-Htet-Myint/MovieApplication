package com.example.myapplication.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.vos.MovieVO
import com.example.myapplication.domain.Trending
import com.example.myapplication.network.dataagents.RetrofitDataAgentImpl
import kotlinx.coroutines.launch

class MovieListViewModel(private val app: Application): AndroidViewModel(app) {
    val upComingMoviesLiveData : MutableLiveData<MovieVO> = MutableLiveData()
    val popularMoviesLiveData : MutableLiveData<MovieVO> = MutableLiveData()
    val topRatedMoviesLiveData : MutableLiveData<MovieVO> = MutableLiveData()
    val allTrendingLiveData : MutableLiveData<List<Trending>> = MutableLiveData()

    fun loadUpComingList(){
        val retrofitDataAgent = RetrofitDataAgentImpl(app)
        viewModelScope.launch {
            upComingMoviesLiveData.value = retrofitDataAgent.getUpComingMovies()
        }
    }

   fun loadPopularList(){
       val retrofitDataAgentImpl = RetrofitDataAgentImpl(app)
       viewModelScope.launch {
           popularMoviesLiveData.value = retrofitDataAgentImpl.getPopularMovies()
       }
   }

   fun loadTopRatedList(){
       val retrofitDataAgentImpl = RetrofitDataAgentImpl(app)
       viewModelScope.launch {
           topRatedMoviesLiveData.value = retrofitDataAgentImpl.getTopRatedMovies()
       }
   }

   fun loadAllTrending(mediaType: String, timeWindow: String){
        val retrofitDataAgentImpl = RetrofitDataAgentImpl(app)
       viewModelScope.launch {
           allTrendingLiveData.value = retrofitDataAgentImpl.getAllTrending(mediaType,timeWindow)
       }
   }
}
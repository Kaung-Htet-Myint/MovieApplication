package com.example.myapplication.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.vos.MovieDetailVO
import com.example.myapplication.network.dataagents.RetrofitDataAgentImpl
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val app: Application): AndroidViewModel(app) {
    val detailLiveData : MutableLiveData<MovieDetailVO> = MutableLiveData()

    fun loadDetail(id: Long){
        val retrofitDataAgentImpl = RetrofitDataAgentImpl(app)
        viewModelScope.launch {
            detailLiveData.value = retrofitDataAgentImpl.getMovieDetail(id)
        }
    }
}
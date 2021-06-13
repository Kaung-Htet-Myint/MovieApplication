package com.example.myapplication.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.myapplication.data.vos.ResultsVO
import com.example.myapplication.network.dataagents.RetrofitDataAgentImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TopRatedViewModel(val app: Application): AndroidViewModel(app) {
    var retrofitDataAgentImpl = RetrofitDataAgentImpl(app)
    var topRatedMovieFlow: Flow<PagingData<ResultsVO>>? = null

    fun loadTopRated(movieType: String){
        viewModelScope.launch {
            topRatedMovieFlow = retrofitDataAgentImpl.getPagingMovies(movieType)
        }

    }

}
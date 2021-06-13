package com.example.myapplication.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.myapplication.data.vos.ResultsVO
import com.example.myapplication.network.dataagents.RetrofitDataAgentImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PagingUpComingViewModel(val app: Application): AndroidViewModel(app) {
    val retrofitDataAgentImpl = RetrofitDataAgentImpl(app)
    var pagingUpComingMoviesFlow : Flow<PagingData<ResultsVO>>? = null

    fun loadPagingMovies(movieType: String){
        viewModelScope.launch {
            pagingUpComingMoviesFlow = retrofitDataAgentImpl.getPagingMovies(movieType)
        }
    }
}
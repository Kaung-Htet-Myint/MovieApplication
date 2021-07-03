package com.example.myapplication.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.myapplication.domain.Movie
import com.example.myapplication.network.dataagents.RetrofitDataAgentImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PagingUpComingViewModel @Inject constructor(val retrofitDataAgentImpl: RetrofitDataAgentImpl): ViewModel() {

    var pagingUpComingMoviesFlow : Flow<PagingData<Movie>>? = null

    fun loadPagingMovies(movieType: String){
        viewModelScope.launch {
            pagingUpComingMoviesFlow = retrofitDataAgentImpl.getPagingMovies(movieType)
        }
    }
}
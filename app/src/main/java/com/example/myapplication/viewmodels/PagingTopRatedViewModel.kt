package com.example.myapplication.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.myapplication.data.vos.Movie
import com.example.myapplication.network.dataagents.RetrofitDataAgentImpl
import com.example.myapplication.persistance.entities.MovieEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PagingTopRatedViewModel @Inject constructor(var retrofitDataAgentImpl : RetrofitDataAgentImpl): ViewModel() {

    var topRatedMovieFlow: Flow<PagingData<Movie>>? = null

    fun loadTopRated(movieType: String){
        viewModelScope.launch {
            topRatedMovieFlow = retrofitDataAgentImpl.getPagingMovies(movieType)
        }

    }

}
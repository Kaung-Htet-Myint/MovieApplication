package com.example.myapplication.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.myapplication.data.vos.ResultsVO
import com.example.myapplication.network.dataagents.RetrofitDataAgentImpl
import com.example.myapplication.persistance.entities.MovieEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopRatedViewModel @Inject constructor(var retrofitDataAgentImpl : RetrofitDataAgentImpl): ViewModel() {

    var topRatedMovieFlow: Flow<PagingData<MovieEntity>>? = null

    fun loadTopRated(movieType: String){
        viewModelScope.launch {
            topRatedMovieFlow = retrofitDataAgentImpl.getPagingMovies(movieType)
        }

    }

}
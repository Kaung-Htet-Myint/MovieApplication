package com.example.myapplication.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.myapplication.data.vos.Movie
import com.example.myapplication.network.dataagents.RetrofitDataAgentImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(val retrofitDataAgentImpl: RetrofitDataAgentImpl): ViewModel() {

    var getDiscoverFlow: Flow<PagingData<Movie>>? = null

    fun loadDiscover(genre: Int){
        viewModelScope.launch {
            getDiscoverFlow = retrofitDataAgentImpl.getGenreMovies(genre)
        }
    }

}
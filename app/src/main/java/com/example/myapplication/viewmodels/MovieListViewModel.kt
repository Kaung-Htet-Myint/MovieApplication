package com.example.myapplication.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.myapplication.data.vos.MovieVO
import com.example.myapplication.domain.Trending
import com.example.myapplication.network.dataagents.RetrofitDataAgentImpl
import com.example.myapplication.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    val retrofitDataAgentImpl: RetrofitDataAgentImpl
) : ViewModel() {
    private val _upComingMoviesLiveData = MutableLiveData<ViewState<MovieVO>>()
    val upComingMoviesLiveData: LiveData<ViewState<MovieVO>>
    get() = _upComingMoviesLiveData

    private val _popularMoviesLiveData = MutableLiveData<ViewState<MovieVO>>()
    val popularMoviesLiveData: LiveData<ViewState<MovieVO>>
    get() = _popularMoviesLiveData

    private val _topRatedMoviesLiveData = MutableLiveData<ViewState<MovieVO>>()
    val topRatedMoviesLiveData: LiveData<ViewState<MovieVO>>
    get() = _topRatedMoviesLiveData

    private val _allTrendingLiveData = MutableLiveData<ViewState<List<Trending>>>()
    val allTrendingLiveData: LiveData<ViewState<List<Trending>>>
    get() = _allTrendingLiveData


    fun loadUpComingList() {
        viewModelScope.launch {
            try {
                _upComingMoviesLiveData.value = ViewState.Loading
                _upComingMoviesLiveData.value = ViewState.Successs(retrofitDataAgentImpl.getUpComingMovies())
            }catch (e: Exception){
                _upComingMoviesLiveData.value = ViewState.Error(e)
            }

        }
    }

    fun loadPopularList() {
        viewModelScope.launch {
            try {
                _popularMoviesLiveData.value = ViewState.Loading
                _popularMoviesLiveData.value = ViewState.Successs(retrofitDataAgentImpl.getPopularMovies())
            }catch (e: Exception){
                _popularMoviesLiveData.value = ViewState.Error(e)
            }
        }
    }

    fun loadTopRatedList() {
        viewModelScope.launch {
            try {
                _topRatedMoviesLiveData.value = ViewState.Loading
                _topRatedMoviesLiveData.value = ViewState.Successs(retrofitDataAgentImpl.getTopRatedMovies())
            }catch (e: Exception){
                _topRatedMoviesLiveData.value = ViewState.Error(e)
            }
        }
    }

    fun loadAllTrending(mediaType: String, timeWindow: String) {

        viewModelScope.launch {
            try {
                _allTrendingLiveData.value = ViewState.Loading
                _allTrendingLiveData.value = ViewState.Successs(retrofitDataAgentImpl.getAllTrending(mediaType,timeWindow))
            }catch (e: Exception){
                _allTrendingLiveData.value = ViewState.Error(e)
            }
        }
    }
}
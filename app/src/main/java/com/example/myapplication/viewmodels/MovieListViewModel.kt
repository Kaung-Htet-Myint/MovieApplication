package com.example.myapplication.viewmodels

import androidx.lifecycle.*
import com.example.myapplication.data.vos.asEntity
import com.example.myapplication.domain.Trending
import com.example.myapplication.network.dataagents.RetrofitDataAgentImpl
import com.example.myapplication.persistance.AppDatabase
import com.example.myapplication.persistance.entities.MovieEntity
import com.example.myapplication.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    val retrofitDataAgentImpl: RetrofitDataAgentImpl,
    val appDatabase : AppDatabase
) : ViewModel() {

    init {
        saveUpComingList()
        savePopularList()
        saveTopRatedList()
    }

    private val _upComingMoviesLiveData = MutableLiveData<ViewState<List<MovieEntity>>>()
    val upComingMoviesLiveData: LiveData<ViewState<List<MovieEntity>>>
    get() = _upComingMoviesLiveData

    private val _popularMoviesLiveData = MutableLiveData<ViewState<List<MovieEntity>>>()
    val popularMoviesLiveData: LiveData<ViewState<List<MovieEntity>>>
    get() = _popularMoviesLiveData

    private val _topRatedMoviesLiveData = MutableLiveData<ViewState<List<MovieEntity>>>()
    val topRatedMoviesLiveData: LiveData<ViewState<List<MovieEntity>>>
    get() = _topRatedMoviesLiveData

    private val _allTrendingLiveData = MutableLiveData<ViewState<List<Trending>>>()
    val allTrendingLiveData: LiveData<ViewState<List<Trending>>>
    get() = _allTrendingLiveData


    fun loadUpComingList() {
        viewModelScope.launch {
            try {
                _upComingMoviesLiveData.value = ViewState.Loading
                _upComingMoviesLiveData.value = ViewState.Successs(
                    withContext(Dispatchers.IO){
                        appDatabase.movieDao().getMovies()
                    })

            }catch (e: Exception){
                _upComingMoviesLiveData.value = ViewState.Error(e)
            }
        }
    }

    fun saveUpComingList(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                retrofitDataAgentImpl.getUpComingMovies().also {
                    appDatabase.movieDao().insertAllMovies(it.results.map {resultVO ->
                        resultVO.asEntity() })
                }
            }
        }
    }

    fun loadPopularList() {
        viewModelScope.launch {
            try {
                _popularMoviesLiveData.value = ViewState.Loading
                _popularMoviesLiveData.value = ViewState.Successs(
                    withContext(Dispatchers.IO){
                        appDatabase.movieDao().getMovies()
                    })
            }catch (e: Exception){
                _popularMoviesLiveData.value = ViewState.Error(e)
            }
        }
    }

    fun savePopularList(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                retrofitDataAgentImpl.getPopularMovies().also {
                    appDatabase.movieDao().insertAllMovies(it.results.map { resultVO ->
                        resultVO.asEntity() })
                }
            }
        }
    }

    fun loadTopRatedList() {
        viewModelScope.launch {
            try {
                _topRatedMoviesLiveData.value = ViewState.Loading
                _topRatedMoviesLiveData.value = ViewState.Successs(
                    withContext(Dispatchers.IO){
                        appDatabase.movieDao().getMovies()
                    }
                )
            }catch (e: Exception){
                _topRatedMoviesLiveData.value = ViewState.Error(e)
            }
        }
    }

    fun saveTopRatedList(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                retrofitDataAgentImpl.getTopRatedMovies().also {
                    appDatabase.movieDao().insertAllMovies(retrofitDataAgentImpl.getTopRatedMovies().results.map { resultVO->
                        resultVO.asEntity() })
                }
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
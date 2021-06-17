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

    val upComingMoviesLiveData = appDatabase.movieDao().getUpComingMovies("upComing")

    val popularMoviesLiveData = appDatabase.movieDao().getPopularMovies("popular")

    val topRatedMoviesLiveData = appDatabase.movieDao().getTopRatedMovies("topRated")

    private val _allTrendingLiveData = MutableLiveData<ViewState<List<Trending>>>()
    val allTrendingLiveData: LiveData<ViewState<List<Trending>>>
    get() = _allTrendingLiveData

    fun saveUpComingList(){
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO){
                    retrofitDataAgentImpl.getUpComingMovies().also {
                        appDatabase.movieDao().insertAllMovies(it.results.map {resultVO ->
                            resultVO.asEntity("upComing") })
                    }
                }
            } catch (e: Exception) {

            }
        }
    }

    fun savePopularList(){
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO){
                    retrofitDataAgentImpl.getPopularMovies().also {
                        appDatabase.movieDao().insertAllMovies(it.results.map { resultVO ->
                            resultVO.asEntity("popular") })
                    }
                }
            }catch (e: Exception){

            }
        }
    }

    fun saveTopRatedList(){
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO){
                    retrofitDataAgentImpl.getTopRatedMovies().also {
                        appDatabase.movieDao().insertAllMovies(retrofitDataAgentImpl.getTopRatedMovies().results.map { resultVO->
                            resultVO.asEntity("topRated") })
                    }
                }
            }catch (e: Exception){

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
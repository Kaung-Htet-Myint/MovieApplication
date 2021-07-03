package com.example.myapplication.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.example.myapplication.domain.Movie
import com.example.myapplication.network.dataagents.RetrofitDataAgentImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val retrofitDataAgentImpl: RetrofitDataAgentImpl
) : ViewModel() {
    private val _allSearchMovies = MutableLiveData<List<Movie>>()
    val allSearchMovies: LiveData<List<Movie>>
        get() = _allSearchMovies

    fun loadSearchMovies(query: String) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO){
                    _allSearchMovies.postValue(retrofitDataAgentImpl.getSearchMovies(query))
                }
            } catch (e: Exception) {
                Log.e("search fail",e.message.orEmpty())
            }
        }
    }
}
package com.example.myapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.vos.MovieDetailVO
import com.example.myapplication.network.dataagents.RetrofitDataAgentImpl
import com.example.myapplication.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(val retrofitDataAgentImpl: RetrofitDataAgentImpl) :
    ViewModel() {
    private val _detailLiveData =  MutableLiveData<ViewState<MovieDetailVO>>()
    val detailLiveData: LiveData<ViewState<MovieDetailVO>>

    get() = _detailLiveData

    fun loadDetail(id: Long) {
        viewModelScope.launch {

            try {
                _detailLiveData.value = ViewState.Loading
                _detailLiveData.value = ViewState.Successs(retrofitDataAgentImpl.getMovieDetail(id))
            }catch (e: Exception){
                _detailLiveData.value = ViewState.Error(e)
            }
        }
    }
}
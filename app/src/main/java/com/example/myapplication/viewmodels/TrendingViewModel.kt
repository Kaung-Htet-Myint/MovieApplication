package com.example.myapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.Trending
import com.example.myapplication.network.dataagents.RetrofitDataAgentImpl
import com.example.myapplication.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(val retrofitDataAgentImpl: RetrofitDataAgentImpl) :
    ViewModel() {

    private val _trendingMovieLiveData = MutableLiveData<ViewState<List<Trending>>>()
    val trendingMovieLiveData: LiveData<ViewState<List<Trending>>>
        get() = _trendingMovieLiveData

    private val _trendingTvLiveData = MutableLiveData<ViewState<List<Trending>>>()
    val trendingTvLiveData: LiveData<ViewState<List<Trending>>>
        get() = _trendingTvLiveData

    private val _trendingPersonLiveData = MutableLiveData<ViewState<List<Trending>>>()
    val trendingPersonLiveData: LiveData<ViewState<List<Trending>>>
        get() = _trendingPersonLiveData

    fun loadTrending(mediaType: String, timeWindow: String) {
        viewModelScope.launch {
            try {
                when (mediaType) {
                    "movie" -> {
                        _trendingMovieLiveData.value = ViewState.Loading
                        _trendingMovieLiveData.value = ViewState.Successs(
                            retrofitDataAgentImpl.getAllTrending(
                                mediaType,
                                timeWindow
                            )
                        )
                    }
                    "tv" -> {
                        _trendingTvLiveData.value = ViewState.Loading
                        _trendingTvLiveData.value = ViewState.Successs(
                            retrofitDataAgentImpl.getAllTrending(
                                mediaType,
                                timeWindow
                            )
                        )
                    }
                    else -> {
                        _trendingPersonLiveData.value = ViewState.Loading
                        _trendingPersonLiveData.value = ViewState.Successs(
                            retrofitDataAgentImpl.getAllTrending(
                                mediaType,
                                timeWindow
                            )
                        )
                    }
                }

            } catch (e: Exception) {
                _trendingMovieLiveData.value = ViewState.Error(e)
            }
        }
    }
}
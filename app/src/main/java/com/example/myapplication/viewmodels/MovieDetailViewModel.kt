package com.example.myapplication.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.MovieDetail
import com.example.myapplication.domain.asEntity
import com.example.myapplication.network.dataagents.RetrofitDataAgentImpl
import com.example.myapplication.persistance.AppDatabase
import com.example.myapplication.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(val retrofitDataAgentImpl: RetrofitDataAgentImpl,
                                               val appDatabase: AppDatabase) :
    ViewModel() {
    private val _detailLiveData =  MutableLiveData<ViewState<MovieDetail>>()
    val detailLiveData: LiveData<ViewState<MovieDetail>>

    get() = _detailLiveData

    var isSuccessfulFavorite = MutableLiveData<ViewState<Boolean>>()

    var isFavorite = MutableLiveData<Boolean>()

    var _isFavoritMovieLiveData = MutableLiveData<Boolean>()
    val isFavoritedMovieLiveData : LiveData<Boolean>
    get() = _isFavoritMovieLiveData

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

    fun loadFavoritedMovies(id: Long){
        viewModelScope.launch {
            try {
                _isFavoritMovieLiveData.value = retrofitDataAgentImpl.getFavoritedMovies(id)
            }catch (e: Exception){
                Log.e("load favorite","fail",e)
            }
        }
    }

    fun addFav(mediaId: Long){
        viewModelScope.launch {
            try {
                isSuccessfulFavorite.value = ViewState.Loading
                isSuccessfulFavorite.value = ViewState.Successs(retrofitDataAgentImpl.postFavoriteMovies(mediaId, true).also {
                    appDatabase.movieDao().updateMovies(mediaId, it)
                })
                isFavorite.value = true
            }catch (e: Exception){
                isSuccessfulFavorite.value = ViewState.Error(e)
            }
        }
    }

    fun removeFav(mediaId: Long){
        viewModelScope.launch {
            try {
                isSuccessfulFavorite.value = ViewState.Loading
                isSuccessfulFavorite.value = ViewState.Successs(retrofitDataAgentImpl.postFavoriteMovies(mediaId, false).also {
                    appDatabase.movieDao().updateMovies(mediaId, it)
                })
                isFavorite.value = false
            }catch (e: Exception){
                Log.e("Save to fav","fail",e)
            }
        }
    }
}

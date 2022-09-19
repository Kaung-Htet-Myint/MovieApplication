package com.example.myapplication.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.example.myapplication.domain.MovieDetail
import com.example.myapplication.network.dataagents.RetrofitDataAgentImpl
import com.example.myapplication.persistance.AppDatabase
import com.example.myapplication.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    val retrofitDataAgentImpl: RetrofitDataAgentImpl,
    val appDatabase: AppDatabase
) :
    ViewModel() {
    private val _detailLiveData = MutableLiveData<ViewState<MovieDetail>>()
    val detailLiveData: LiveData<ViewState<MovieDetail>>
        get() = _detailLiveData

    var isSuccessfulFavorite = MutableLiveData<ViewState<Boolean>>()

    var isFavorite = MutableLiveData<Boolean>()

    var _isFavoritMovieLiveData = MutableLiveData<Boolean>()
    val isFavoritedMovieLiveData: LiveData<Boolean>
        get() = _isFavoritMovieLiveData

    /* var _movieDetailsLiveData = MutableLiveData<MovieDetail>()
     val movieDetailLiveData : LiveData<MovieDetail>
     get() = _movieDetailsLiveData*/

    var movieId: MutableLiveData<Long> = MutableLiveData()
    val movieDetailLiveData = movieId.asFlow()
        .filterNotNull()
        .distinctUntilChanged()
        .flatMapConcat { appDatabase.movieDao().getMovieDetails(it) }
        .filterNotNull()
        .map {
            it.asDomain() }
        .asLiveData()

    var id: Long = 0

    fun loadMovieDetail(id: Long) {
        if (this.id == id) return
        viewModelScope.launch {
            try {
                retrofitDataAgentImpl.getMovieDetail(id)
                this@MovieDetailViewModel.id = id
            } catch (e: Exception) {
                ViewState.Error(e)
            }
        }
    }

    fun getMovieId(id: Long) {
        movieId.value = id
    }

    fun loadFavoritedMovies(id: Long) {
        viewModelScope.launch {
            try {
                _isFavoritMovieLiveData.value = retrofitDataAgentImpl.getFavoritedMovies(id)
            } catch (e: Exception) {
                Log.e("load favorite", "fail", e)
            }
        }
    }

    fun addFav(mediaId: Long) {
        viewModelScope.launch {
            try {
                isSuccessfulFavorite.value = ViewState.Loading
                isSuccessfulFavorite.value = ViewState.Successs(
                    retrofitDataAgentImpl.postFavoriteMovies(mediaId, true).also {
                        appDatabase.movieDao().updateMovies(mediaId, it)
                    })
                isFavorite.value = true
            } catch (e: Exception) {
                isSuccessfulFavorite.value = ViewState.Error(e)
            }
        }
    }

    fun removeFav(mediaId: Long) {
        viewModelScope.launch {
            try {
                isSuccessfulFavorite.value = ViewState.Loading
                isSuccessfulFavorite.value = ViewState.Successs(
                    retrofitDataAgentImpl.postFavoriteMovies(mediaId, false).also {
                        appDatabase.movieDao().updateMovies(mediaId, it)
                    })
                isFavorite.value = false
            } catch (e: Exception) {
                Log.e("Save to fav", "fail", e)
            }
        }
    }
}


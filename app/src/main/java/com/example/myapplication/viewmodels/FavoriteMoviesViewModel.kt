package com.example.myapplication.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.myapplication.domain.Movie
import com.example.myapplication.network.dataagents.RetrofitDataAgentImpl
import com.example.myapplication.repositories.FavMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteMoviesViewModel @Inject constructor(val retrofitDataAgentImpl: RetrofitDataAgentImpl,
                                                  private val repository: FavMoviesRepository): ViewModel() {
    var pagingfavoriteMoviesFlow : Flow<PagingData<Movie>>? = null

    fun loadFavMovies(){
        viewModelScope.launch {
            try {
               // pagingfavoriteMoviesFlow = retrofitDataAgentImpl.getPagingFavoriteMovies()
                pagingfavoriteMoviesFlow = repository.postOfFavMovies(20)
            }catch (e: Exception){
                Log.e("load fav movies","fail",e)
            }
        }
    }
}
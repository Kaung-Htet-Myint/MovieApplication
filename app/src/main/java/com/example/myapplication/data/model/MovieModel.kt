package com.example.myapplication.data.model

import androidx.paging.PagingData
import com.example.myapplication.data.vos.MovieDetailVO
import com.example.myapplication.data.vos.MovieVO
import com.example.myapplication.data.vos.ResultsVO
import com.example.myapplication.data.vos.TrandingMoviesVO
import kotlinx.coroutines.flow.Flow


interface MovieModel {
    fun getUpcomingMovies(onSuccess: (MovieVO)-> Unit, onFailure: (String)-> Unit)
    fun getMoviesPagingMovies(): Flow<PagingData<ResultsVO>>
    fun getMovieDetail(id: Long, onSuccess: (MovieDetailVO) -> Unit, onFailure: (String) -> Unit)
    fun getPopularMovies(onSuccess: (MovieVO) -> Unit, onFailure: (String) -> Unit)
    fun getTopRatedMovies(onSuccess: (MovieVO) -> Unit, onFailure: (String) -> Unit)
    fun getTrandingMovies(onSuccess: (TrandingMoviesVO) -> Unit, onFailure: (String) -> Unit)
}
package com.example.myapplication.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.example.myapplication.domain.MovieGenre
import com.example.myapplication.domain.Movie
import com.example.myapplication.domain.Trending
import com.example.myapplication.domain.asEntity
import com.example.myapplication.network.dataagents.RetrofitDataAgentImpl
import com.example.myapplication.persistance.AppDatabase
import com.example.myapplication.persistance.entities.*
import com.example.myapplication.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    val retrofitDataAgentImpl: RetrofitDataAgentImpl,
    val appDatabase: AppDatabase
) : ViewModel() {

    val upComingMoviesLiveData = appDatabase.movieDao().getMovies("upComing").map {
        it.map {
            it.asDomain()
        }
    }

    val popularMoviesLiveData = appDatabase.movieDao().getMovies("popular").map {
        it.map {
            it.asDomain()
        }
    }

    val topRatedMoviesLiveData = appDatabase.movieDao().getMovies("topRated").map {
        it.map {
            it.asDomain()
        }
    }

    private val _allTrendingLiveData = MutableLiveData<ViewState<List<Trending>>>()
    val allTrendingLiveData: LiveData<ViewState<List<Trending>>>
        get() = _allTrendingLiveData

    private val _movieGenresLiveData = MutableLiveData<MovieGenre>()
    val movieGenresLiveData: LiveData<MovieGenre>
    get() = _movieGenresLiveData

    init {
        saveUpComingList()
        savePopularList()
        saveTopRatedList()
        loadAllTrending( "all",  "day")
    }

    fun saveUpComingList() {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    retrofitDataAgentImpl.getUpComingMovies().also {
                        // save movie entities
                        appDatabase.movieDao().insertAllMovies(it.map { movie ->
                            movie.asEntity("upComing")
                        })

                        // save movie genres
                        val movieGeneres: List<GenreEntity> = it.flatMap { movie ->
                            movie.genreIds.map {
                                GenreEntity(it)
                            }
                        }
                        appDatabase.genreDao().insert(movieGeneres)

                        // save movie and genres join table
                        val movieAndGenres: List<MovieGenreEntity> = it.flatMap { movie ->
                            movie.genreIds.map { genreId ->
                                MovieGenreEntity(
                                    id = "upComing"+movie.id.toString(),
                                    genreId = genreId
                                )
                            }
                        }
                        appDatabase.movieWithGenre().insert(movieAndGenres)
                    }
                }
            } catch (e: Exception) {
                    Log.e("UpComing Movies",e.message.orEmpty())
            }
        }
    }

    fun savePopularList() {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    retrofitDataAgentImpl.getPopularMovies().also {
                        appDatabase.movieDao().insertAllMovies(it.map {
                            it.asEntity("popular")
                        })

                        // save movie genres
                        val movieGeneres: List<GenreEntity> = it.flatMap { movie ->
                            movie.genreIds.map {
                                GenreEntity(it)
                            }
                        }
                        appDatabase.genreDao().insert(movieGeneres)

                        // save movie and genres join table
                        val movieAndGenres: List<MovieGenreEntity> = it.flatMap { movie ->
                            movie.genreIds.map { genreId ->
                                MovieGenreEntity(
                                    id = "upComing"+movie.id.toString(),
                                    genreId = genreId
                                )
                            }
                        }
                        appDatabase.movieWithGenre().insert(movieAndGenres)
                    }
                }
            } catch (e: Exception) {
                Log.e("Popular Movies",e.message.orEmpty())
            }
        }
    }

    fun saveTopRatedList() {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    retrofitDataAgentImpl.getTopRatedMovies().also {
                        appDatabase.movieDao()
                            .insertAllMovies(it.map { movie ->
                                movie.asEntity("topRated")
                            })
                        // save movie genres
                        val movieGeneres: List<GenreEntity> = it.flatMap { movie ->
                            movie.genreIds.map {
                                GenreEntity(it)
                            }
                        }
                        appDatabase.genreDao().insert(movieGeneres)

                        // save movie and genres join table
                        val movieAndGenres: List<MovieGenreEntity> = it.flatMap { movie ->
                            movie.genreIds.map { genreId ->
                                MovieGenreEntity(
                                    id = "upComing"+movie.id.toString(),
                                    genreId = genreId
                                )
                            }
                        }
                        appDatabase.movieWithGenre().insert(movieAndGenres)
                    }
                }
            } catch (e: Exception) {
                Log.e("TopRated Movies",e.message.orEmpty())
            }
        }
    }

    //for banner view
    fun loadAllTrending(mediaType: String, timeWindow: String) {

        viewModelScope.launch {
            try {
                _allTrendingLiveData.value = ViewState.Loading
                _allTrendingLiveData.value =
                    ViewState.Successs(retrofitDataAgentImpl.getAllTrending(mediaType, timeWindow))
            } catch (e: Exception) {
                _allTrendingLiveData.value = ViewState.Error(e)
            }
        }
    }

    fun loadMovieGenre(){
        viewModelScope.launch {
            try {
                _movieGenresLiveData.value = retrofitDataAgentImpl.getMovieGenre()
            }catch (e: Exception){
                Log.e("Movie Genre call Fail",e.message.orEmpty())
            }
        }
    }
}

fun MoviesWithGenre.asDomain(): Movie {
    return Movie(
        backdropPath = movie.backdropPath,
        genreIds = genre.map {
            it.genreId
        },
        id = movie.movieId,
        originalLanguage = movie.originalLanguage,
        originalTitle = movie.originalTitle,
        overview = movie.overview,
        popularity = movie.popularity,
        posterPath = movie.posterPath,
        releaseDate = movie.releaseDate,
        title = movie.title,
        video = movie.video,
        voteAverage = movie.voteAverage,
        isFavorite = false,
        voteCount = movie.voteCount
    )
}

fun MovieEntity.asDomain(genreId: List<Int>): Movie {
    return Movie(
        backdropPath = backdropPath,
        genreIds = genreId,
        id = movieId,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        isFavorite = isFavorite,
        voteCount = voteCount
    )
}

fun GenreWithMovies.asDomain(): List<Movie> {
    val movieEntities = this.movie
    // god style
    return movieEntities.map {
        it.asDomain(listOf(genre.genreId))
    }
/*
    // junior kids
    val movieDomainList: MutableList<ResultsVO> = mutableListOf()

    for (i in movieEntities.indices) {
        val movieDomain = movieEntities.get(i).asDomain(listOf(genre.genreId))
        movieDomainList.add(movieDomain)
    }

    return movieDomainList*/
}

fun <I, O> List<I>.myMap(op: (I) -> O): List<O> {
    val outputList: MutableList<O> = mutableListOf()

    for (i in this.indices) {
        outputList.add(op(this[i]))
    }

    return outputList
}
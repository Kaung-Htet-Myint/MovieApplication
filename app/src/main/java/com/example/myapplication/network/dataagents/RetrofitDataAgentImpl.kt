package com.example.myapplication.network.dataagents

import com.example.myapplication.ACCESS_TOKEN
import com.example.myapplication.BASE_URL
import com.example.myapplication.data.vos.MovieDetailVO
import com.example.myapplication.data.vos.MovieVO
import com.example.myapplication.network.MovieApi
import com.example.myapplication.network.responses.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitDataAgentImpl : MovieDataAgent {
    var movieApi: MovieApi

    init {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        movieApi = retrofit.create(MovieApi::class.java)
    }
    override fun getUpComingMovies(
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val upComingMoviesCall = movieApi.getUpcomingMovieResponse(ACCESS_TOKEN)

            upComingMoviesCall.enqueue(object : Callback<GetMovieResponse> {
            override fun onFailure(call: Call<GetMovieResponse>, t: Throwable) {
                onFailure(t.localizedMessage)
            }

            override fun onResponse(
                call: Call<GetMovieResponse>,
                response: Response<GetMovieResponse>
            ) {
                val upcomingMovieResponse = response.body()

                if (upcomingMovieResponse != null) {
                    if (upcomingMovieResponse.results != null) {
                        onSuccess(upcomingMovieResponse.asDomain())
                    } else {
                        onFailure("Movie Response Fail!")
                    }
                } else {
                    onFailure("Network Fail")
                }
            }

        })
    }

    override fun getMovieDetail(id: Long,onSuccess: (MovieDetailVO) -> Unit, onFailure: (String) -> Unit) {
        val movieDetailCall = movieApi.getDetailResponse(id,ACCESS_TOKEN)
        movieDetailCall.enqueue(object : Callback<GetDetailResponse>{
            override fun onFailure(call: Call<GetDetailResponse>, t: Throwable) {
                onFailure(t.localizedMessage)
            }

            override fun onResponse(
                call: Call<GetDetailResponse>,
                response: Response<GetDetailResponse>
            ) {
                val movieDetailResponse = response.body()

                if (movieDetailResponse != null){
                    if (movieDetailResponse.backdrop_path != null){
                        onSuccess(movieDetailResponse.asDomain())
                    }else{
                        onFailure("Detail Response Fail!")
                    }
                }else{
                    onFailure("Network Fail!")
                }
            }
        })
    }

    override fun getPopularMoives(
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val popularMoviesCall = movieApi.getPopularResponse(ACCESS_TOKEN)
        popularMoviesCall.enqueue(object : Callback<GetMovieResponse>{
            override fun onFailure(call: Call<GetMovieResponse>, t: Throwable) {
                onFailure(t.localizedMessage)
            }

            override fun onResponse(
                call: Call<GetMovieResponse>,
                response: Response<GetMovieResponse>
            ) {
                val popularResponse = response.body()

                if (popularResponse != null){
                    if (popularResponse.results != null){
                        onSuccess(popularResponse.asDomain())
                    }else{
                        onFailure("Popular Movies Response Fail!")
                    }
                }else{
                    onFailure("Network Fail!!")
                }
            }
        })
    }

    override fun getTopRatedMovies(onSuccess: (MovieVO) -> Unit, onFailure: (String) -> Unit) {
        val topRatedCall = movieApi.getTopRatedResponse(ACCESS_TOKEN)
        topRatedCall.enqueue(object : Callback<GetMovieResponse>{
            override fun onFailure(call: Call<GetMovieResponse>, t: Throwable) {
                onFailure(t.localizedMessage)
            }

            override fun onResponse(
                call: Call<GetMovieResponse>,
                response: Response<GetMovieResponse>
            ) {
                val topRatedResponse = response.body()

                if (topRatedResponse != null){
                    if (topRatedResponse.results != null){
                        onSuccess(topRatedResponse.asDomain())
                    }else{
                        onFailure("Top Rated Movies Response Fail!")
                    }
                }else{
                    onFailure("Network Fail!!")
                }
            }
        })
    }


}
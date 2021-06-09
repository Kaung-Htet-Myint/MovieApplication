package com.example.myapplication.network

import com.example.myapplication.GET_DETAIL
import com.example.myapplication.GET_POPULAR
import com.example.myapplication.GET_TOPRATED
import com.example.myapplication.GET_UPCOMING
import com.example.myapplication.network.responses.GetDetailResponse
import com.example.myapplication.network.responses.GetMovieResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET(GET_UPCOMING)
    fun getUpcomingMovieResponse(@Query("api_key") apiKey: String): Call<GetMovieResponse>

    @GET(GET_UPCOMING)
    suspend fun getUpcomingMoviePagingResponse(@Query("api_key") apiKey: String,
                                         @Query("page")page: Int): Response<GetMovieResponse>

    @GET(GET_DETAIL)
    fun getDetailResponse(@Path("id") movieId: Long,@Query("api_key") apiKey: String): Call<GetDetailResponse>

    @GET(GET_POPULAR)
    fun getPopularResponse(@Query("api_key") apiKey: String): Call<GetMovieResponse>

    @GET(GET_TOPRATED)
    fun getTopRatedResponse(@Query("api_key") apiKey: String): Call<GetMovieResponse>
}

package com.example.myapplication.network

import com.example.myapplication.*
import com.example.myapplication.network.responses.GetDetailResponse
import com.example.myapplication.network.responses.GetMovieResponse
import com.example.myapplication.network.responses.TrendingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET(GET_UPCOMING)
    suspend fun getUpcomingMovieResponse(@Query("api_key") apiKey: String): GetMovieResponse

    @GET(GET_UPCOMING)
    suspend fun getUpcomingMoviePagingResponse(@Query("api_key") apiKey: String,
                                         @Query("page")page: Int): Response<GetMovieResponse>

    @GET(GET_DETAIL)
    suspend fun getMovieDetail(@Path("id") movieId: Long,@Query("api_key") apiKey: String): GetDetailResponse

    @GET(GET_POPULAR)
    suspend fun getPopularMoviePagingResponse(@Query("api_key") apiKey: String,
                                               @Query("page")page: Int): Response<GetMovieResponse>

    @GET(GET_POPULAR)
    suspend fun getPopularResponse(@Query("api_key") apiKey: String): GetMovieResponse

    @GET(GET_TOPRATED)
    suspend fun getTopRatedResponse(@Query("api_key") apiKey: String): GetMovieResponse

    @GET(GET_TOPRATED)
    suspend fun getTopRatedMoviePagingResponse(@Query("api_key") apiKey: String,
                                              @Query("page")page: Int): Response<GetMovieResponse>

    @GET(GET_TRENDING)
    suspend fun getTrendingResponse(@Path("media_type")mediaType: String,
                            @Path("time_window")timeWindow: String,
                            @Query("api_key") apiKey: String): TrendingResponse
}

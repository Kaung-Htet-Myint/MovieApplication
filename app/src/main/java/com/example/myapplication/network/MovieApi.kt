package com.example.myapplication.network

import com.example.myapplication.*
import com.example.myapplication.network.dto.MovieGenresDto
import com.example.myapplication.network.dto.MovieDetailDto
import com.example.myapplication.network.responses.GetFavoritedMoviesResponse
import com.example.myapplication.network.responses.GetMovieResponse
import com.example.myapplication.network.responses.PostFavoritesResponse
import com.example.myapplication.network.responses.TrendingResponse
import retrofit2.Response
import retrofit2.http.*

interface MovieApi {

    @GET(GET_UPCOMING)
    suspend fun getUpcomingMovieResponse(@Query("api_key") apiKey: String): GetMovieResponse

    @GET(GET_UPCOMING)
    suspend fun getUpcomingMoviePagingResponse(@Query("api_key") apiKey: String,
                                         @Query("page")page: Int): Response<GetMovieResponse>

    @GET(GET_DISCOVER)
    suspend fun getDiscoverResponse(@Query("api_key") apiKey: String,
                                    @Query("page")page: Int,
                                    @Query("with_genres") withGenres: Int): Response<GetMovieResponse>

    @GET(GET_DETAIL)
    suspend fun getMovieDetail(@Path("id") movieId: Long,@Query("api_key") apiKey: String): MovieDetailDto

    @GET(GET_POPULAR)
    suspend fun getPopularMoviePagingResponse(@Query("api_key") apiKey: String,
                                               @Query("page")page: Int): Response<GetMovieResponse>

    @GET(GET_FAVORITE_MOVIES)
    suspend fun getFavMovies(@Query("api_key") apiKey: String,
                             @Query("session_id")sessionId: String,
                             @Query("page")page: Int):Response<GetMovieResponse>

    @GET(GET_FAVORITED_MOVIES_STATUS)
    suspend fun getFavoritedMovies(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String,
        @Query("session_id") sessionId: String
    ): GetFavoritedMoviesResponse


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

    @GET(GET_SEARCH)
    suspend fun getSearchResponse(@Query("api_key") apiKey: String,
                                @Query("query") query: String): GetMovieResponse

    @GET(GET_MOVIE_GENRES)
    suspend fun getMovieGenreResponse(@Query("api_key") apiKey: String): MovieGenresDto

    @Headers("Content-Type: application/json")
    @POST(POST_FAVORITES)
    suspend fun postFavorites(
        @Query("api_key") apiKey: String,
        @Query("session_id") sessionId: String,
        @Body body: FavoritesRequest
    ): PostFavoritesResponse
}

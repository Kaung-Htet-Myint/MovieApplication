package com.example.myapplication.network.responses

class GetFavoritedMoviesResponse(
    val id: Int,
    val favorite: Boolean,
    val rated: Boolean,
    val watchlist: Boolean
)
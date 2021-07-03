package com.example.myapplication.network

data class FavoritesRequest(
    val media_type: String,
    val media_id: Long,
    val favorite: Boolean
)

package com.example.myapplication.data.vos

data class KnownFor(
    val voteCount: Double,
    val title: String,
    val mediaType: String,
    val adult: Boolean,
    val backdropPath: String,
    val id: Long
)
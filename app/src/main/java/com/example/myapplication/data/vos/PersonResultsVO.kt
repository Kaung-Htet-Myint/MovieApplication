package com.example.myapplication.data.vos

data class PersonResultsVO(
    val name: String,
    val known_for: List<KnownForVO>,
    val known_for_department: String,
    val id: Long,
    val profile_path: String,
    val media_type: String
)
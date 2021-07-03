package com.example.myapplication.domain

import com.example.myapplication.data.vos.KnownFor

data class Person(
    val name: String,
    val knownFor: List<KnownFor>,
    val knownForDepartment: String,
    val id: Long,
    val profilePath: String,
    val mediaType: String
)

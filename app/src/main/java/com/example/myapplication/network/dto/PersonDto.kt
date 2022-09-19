package com.example.myapplication.network.dto

import com.example.myapplication.data.vos.KnownFor
import com.example.myapplication.domain.Person

data class PersonDto(
    val name: String,
    val known_for: List<KnownFor>,
    val known_for_department: String,
    val id: Long,
    val profile_path: String,
    val media_type: String
)

fun PersonDto.asDomain(): Person {
    return Person(
        name = name,
        knownFor = known_for,
        knownForDepartment = known_for_department,
        id = id,
        profilePath = profile_path,
        mediaType = media_type
    )
}

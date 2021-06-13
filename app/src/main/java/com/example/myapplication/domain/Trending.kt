package com.example.myapplication.domain

import com.example.myapplication.network.responses.TrendingDto

sealed class Trending {
    data class Person(
        val id: Long,
        val name: String,
        val gender: String?,
        val profilePath: String?,
    ) : Trending()

    data class TV(
        val id: Long,
        val name: String,
        val posterPath: String?,
        val backdropPath: String?,
        val overview: String?
    ) : Trending()

    data class Movie(
        val id: Long,
        val title: String,
        val posterPath: String?,
        val backdropPath: String?,
        val overview: String?
    ) : Trending()

    //object Unknown : Trending()

    fun getIdentifier() = when(this){
        is Person -> id
        is TV -> id
        is Movie -> id
    }

    fun getImage() = when(this){
        is Person -> profilePath
        is TV -> posterPath
        is Movie -> posterPath
    }

    fun getDisplayName() = when(this){
        is Person -> name
        is TV -> name
        is Movie -> title
    }

}

class TrendingMapper() {
        fun map(input: List<TrendingDto>?): List<Trending> {
        return input?.map {
            when (it.media_type) {
                "person" -> {
                    Trending.Person(
                        id = it.id ?: -1,
                        name = it.name.orEmpty(),
                        gender = it.gender.orEmpty(),
                        profilePath = it.profile_path.orEmpty()
                    )
                }
                "movie" -> {
                    Trending.Movie(
                        id = it.id ?: -1,
                        title = it.title.orEmpty(),
                        posterPath = it.poster_path.orEmpty(),
                        backdropPath = it.backdrop_path.orEmpty(),
                        overview = it.overview.orEmpty()
                    )
                }
                "tv" -> {
                    Trending.TV(
                        id = it.id ?: -1,
                        name = it.name.orEmpty(),
                        posterPath = it.poster_path.orEmpty(),
                        backdropPath = it.backdrop_path.orEmpty(),
                        overview = it.overview.orEmpty()
                    )
                }
                // this is us being generous
//                else -> Trending.Unknown
                // this is how we handle, bitch!
                else -> throw UnsupportedOperationException("Unknown media type: ${it.media_type}")
            }
        }.orEmpty()
    }
}


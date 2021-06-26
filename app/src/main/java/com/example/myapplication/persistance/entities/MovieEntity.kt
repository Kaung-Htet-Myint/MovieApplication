package com.example.myapplication.persistance.entities

import androidx.room.*

@Entity
data class MovieEntity(
    @PrimaryKey
    val id : String,

    val movieId: Long,
    val backdropPath: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Float,
    val voteCount: Long,
    val movieType: String
)

@Entity
data class GenreEntity(
    @PrimaryKey
    val genreId : Int
)

@Entity(primaryKeys = ["id","genreId"])
data class MovieGenreEntity(
    val id: String,
    val genreId : Int
)


data class MoviesWithGenre(
    @Embedded val movie: MovieEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "genreId",
        associateBy = Junction(MovieGenreEntity::class)
    )
    val genre : List<GenreEntity>
)


data class GenreWithMovies(
    @Embedded val genre: GenreEntity,
    @Relation(
        parentColumn = "genreId",
        entityColumn = "id",
        associateBy = Junction(MovieGenreEntity::class)
    )
    val movie : List<MovieEntity>
)




package com.example.myapplication.persistance.entities

import androidx.room.*
import com.example.myapplication.domain.Movie

@Entity (tableName = "movie_entity")
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
    val movieType: String,
    val isFavorite: Boolean
)


@Entity (tableName = "fav_movies")
data class FavEntity(
    @PrimaryKey
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
    val voteCount: Long
)

fun FavEntity.asDomain():Movie{
    return Movie(
        backdropPath = backdropPath,
        overview = overview,
        popularity = popularity,
        title = title,
        video = video,
        id = movieId,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        posterPath = posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        voteCount = voteCount,
        isFavorite = false,
        genreIds = emptyList()
    )
}

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




package com.example.moviesappmvpkotlin.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesappmvpkotlin.model.MovieModel

@Entity(tableName =  "movies")
class MovieEntity(
 @PrimaryKey
 @ColumnInfo( name="id")
 var id: Int? = -1,
 val posterPath: String? = null,
 val title: String? = null,
 val overView: String? = null,
 val releaseDate: String? = null,
 val rating: Double = 0.0
)

val MovieEntity.toModel: MovieModel
    get() = MovieModel(
     id = this.id,
     posterPath = this.posterPath,
     title = this.title,
     overView = this.overView,
     releaseDate = this.releaseDate,
     voteAverage = this.rating
    )

package com.example.moviesappmvpkotlin.model

import android.os.Parcelable
import com.example.moviesappmvpkotlin.database.MovieEntity
import com.example.moviesappmvpkotlin.database.toModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieModel(
    @SerializedName("popularity")
    @Expose
    var popularity: Double? = null,
    @SerializedName( "backdrop_path")
    @Expose
    val backdropPath: String? = null,
    @SerializedName( "vote_count")
    @Expose
    val voteCount: Int? = null,
    @SerializedName( "video")
    @Expose
    val video: Boolean? = null,
    @SerializedName( "poster_path")
    @Expose
    internal var posterPath: String? = null,
    @SerializedName( "id")
    @Expose
    val id: Int? = null,
    @SerializedName( "adult")
    @Expose
    val adult: Boolean? = null,

    @SerializedName( "original_language")
    @Expose
    val originalLanguage: String? = null,
    @SerializedName( "original_title")
    @Expose
    val originalTitle: String? = null,
    @SerializedName( "genre_ids")
    @Expose
    val genreIds: List<Int>? = null,
    @SerializedName( "title")
    @Expose
    val title: String? = null,
    @SerializedName( "vote_average")
    @Expose
    val voteAverage: Double = 0.0,
    @SerializedName( "overview")
    @Expose
    val overView: String? = null,
    @SerializedName( "release_date")
    @Expose
    val releaseDate: String? = null
): Parcelable

val MovieModel.getPoster: String
    get() = (posterPath?.let { "http://image.tmdb.org/t/p/w500$posterPath" }
        ?: let { null }) as String
val MovieModel.toEntity:MovieEntity
get() = MovieEntity(
    id = this.id,
    posterPath = this.posterPath,
    title = this.title,
    overView = this.overView,
    releaseDate = this.releaseDate,
    rating = this.voteAverage
)
package com.example.moviesappmvpkotlin.ui.movies.top_rated_movies

import com.example.moviesappmvpkotlin.base.BaseView
import com.example.moviesappmvpkotlin.model.response.MovieResponse

interface TopRatedMoviesView:BaseView {

    fun getMovies(movieResponse: MovieResponse)
    fun isInserted(id:Int)
    fun isDeleted(id:Int)
}
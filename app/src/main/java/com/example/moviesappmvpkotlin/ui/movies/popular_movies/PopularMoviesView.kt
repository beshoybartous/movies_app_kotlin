package com.example.moviesappmvpkotlin.ui.movies.popular_movies

import com.example.moviesappmvpkotlin.base.BaseView
import com.example.moviesappmvpkotlin.model.response.MovieResponse

interface PopularMoviesView : BaseView {

    fun getMovies(movieResponse: MovieResponse)
    fun isInserted(id:Int)
    fun isDeleted(id:Int)

}
package com.example.moviesappmvpkotlin.ui.movies.favourite_movies

import com.example.moviesappmvpkotlin.base.BaseView
import com.example.moviesappmvpkotlin.model.MovieModel

interface FavouriteMoviesView :BaseView{
    fun getMovies(movies: List<MovieModel>)
    fun isDeleted(id: Int?)
}
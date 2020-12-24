package com.example.moviesappmvpkotlin.ui.movies.favourite_movies

import android.content.Context
import android.graphics.Movie
import com.example.moviesappmvpkotlin.base.BasePresenter
import com.example.moviesappmvpkotlin.database.toModel
import com.example.moviesappmvpkotlin.model.MovieModel
import com.example.moviesappmvpkotlin.model.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.*

class FavouriteMoviesPresenter(
    private val view: FavouriteMoviesView, override val context: Context
) :
    BasePresenter(view, context) {
    fun getFavouriteMovies(){
        coroutineScope.launch {
            val result= async(Dispatchers.IO){
                database.movieDao().getMovies()
            }.await()
            result?.let {
                val movieModelList= mutableListOf<MovieModel>()
                for (movie in it) {
                    movieModelList.add(movie.toModel)
                }
                view.getMovies(movieModelList)
            }
        }
    }

    fun deleteData(movie: MovieModel){
        coroutineScope.launch {
            val result=async(Dispatchers.IO){
                database.movieDao().delete(movie.toEntity)
            }.await()
            if(result==0){
                view.isDeleted(-1)
            }
            else{
                view.isDeleted(result)
            }
        }
    }
}
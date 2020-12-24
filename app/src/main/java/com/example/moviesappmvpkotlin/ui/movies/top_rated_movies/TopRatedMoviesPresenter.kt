package com.example.moviesappmvpkotlin.ui.movies.top_rated_movies

import android.content.Context
import com.example.moviesappmvpkotlin.base.BasePresenter
import com.example.moviesappmvpkotlin.model.MovieModel
import com.example.moviesappmvpkotlin.model.payload.MoviePayLoad
import com.example.moviesappmvpkotlin.model.payload.toMap
import com.example.moviesappmvpkotlin.model.response.MovieResponse
import com.example.moviesappmvpkotlin.model.toEntity
import com.example.moviesappmvpkotlin.network.EndPoints
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class TopRatedMoviesPresenter(private val view: TopRatedMoviesView, override val context: Context) :
    BasePresenter(view, context) {
    fun getData(parameter:MoviePayLoad){
        coroutineScope.launch {
            val result = async(Dispatchers.IO){
                networkManager.getData(EndPoints.TOP_RATED_MOVIES,parameter.toMap,MovieResponse::class.java)
            }.await()
            result?.let {
                view.getMovies(it)
            }
        }
    }

    fun insertData(movie:MovieModel){
        coroutineScope.launch {
            val result = async(Dispatchers.IO){
                database.movieDao().insert(movie.toEntity)
            }.await()

            if(result!=null &&  result.toInt()==-1){
                view.isInserted(result.toInt())
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
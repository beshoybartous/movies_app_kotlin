package com.example.moviesappmvpkotlin.ui.movies.popular_movies

import android.content.Context
import android.util.Log
import com.example.moviesappmvpkotlin.base.BasePresenter
import com.example.moviesappmvpkotlin.model.MovieModel
import com.example.moviesappmvpkotlin.model.payload.MoviePayLoad
import com.example.moviesappmvpkotlin.model.payload.toMap
import com.example.moviesappmvpkotlin.model.response.MovieResponse
import com.example.moviesappmvpkotlin.model.toEntity
import com.example.moviesappmvpkotlin.network.EndPoints
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PopularMoviesPresenter (private val view: PopularMoviesView, override val context: Context) :
    BasePresenter(view, context) {
    fun getData(parameter: MoviePayLoad){
        coroutineScope.launch {
            val result = withContext(Dispatchers.IO) {
                 networkManager.getData(
                    EndPoints.POPULAR_MOVIES,
                    parameter.toMap,
                    MovieResponse::class.java
                )

            }
            result?.let {
                view.getMovies(it)
            }
        }
    }

    fun insertData(movie: MovieModel){
        coroutineScope.launch {
            val result = withContext(Dispatchers.IO) {
                 database.movieDao().insert(movie.toEntity)
            }
            Log.d("resultis", "insertData: $result")
            if(result!=null &&  result.toInt()!=-1){
                Log.d("resultis", "insertData: $result")

                view.isInserted(result.toInt())
            }

        }
    }

    fun deleteData(movie: MovieModel){
        coroutineScope.launch {
            val result = withContext(Dispatchers.IO) {
                 database.movieDao().delete(movie.toEntity)

            }
            Log.d("deltesucc", "deleteData: $result")
            if(result==0){
                view.isDeleted(-1)
            }
            else{
                view.isDeleted(movie.id!!)
            }
        }
    }
}
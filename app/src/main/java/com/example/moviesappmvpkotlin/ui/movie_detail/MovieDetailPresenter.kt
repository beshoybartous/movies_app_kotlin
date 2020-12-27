package com.example.moviesappmvpkotlin.ui.movie_detail

import android.content.Context
import android.util.Log
import com.example.moviesappmvpkotlin.base.BasePresenter
import com.example.moviesappmvpkotlin.model.MovieModel
import com.example.moviesappmvpkotlin.model.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailPresenter(val view:MovieDetailView,override val context: Context):
    BasePresenter(view,context) {
    fun insertData(movie:MovieModel){
        coroutineScope.launch {
            val result = withContext(Dispatchers.IO) {
                database.movieDao().insert(movie.toEntity)
            }
            Log.d("isinhere", "insertData:$result ")
            if(result!=null &&  result.toInt()!=-1){

                view.isInserted(true)
            }
        }
    }
    fun deleteData(movie: MovieModel){
        coroutineScope.launch {
            val result = withContext(Dispatchers.IO) {
                database.movieDao().delete(movie.toEntity)
            }
            Log.d("isinhere", "insertData:$result ")

            if(result==0){
                view.isDeleted(false)
            }
            else{
                view.isDeleted(true)
            }
        }
    }
}
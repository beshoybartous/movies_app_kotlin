package com.example.moviesappmvpkotlin.ui.splash_screen

import android.content.Context
import com.example.moviesappmvpkotlin.base.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashScreenPresenter(private val view: SplashScreenView, override val context: Context) :
    BasePresenter(view, context) {

    fun getData() {
        coroutineScope.launch {
            val result = async(Dispatchers.IO) {
                database.movieDao().getMoviesId()
            }
            result.await()?.let {
                view.getMoviesId(it)
            }
        }
    }
}
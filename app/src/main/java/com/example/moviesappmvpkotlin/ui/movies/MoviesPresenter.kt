package com.example.moviesappmvpkotlin.ui.movies

import android.content.Context
import com.example.moviesappmvpkotlin.base.BasePresenter
import com.example.moviesappmvpkotlin.base.BaseView

class MoviesPresenter(view :BaseView,override val context: Context) : BasePresenter(view,context) {
}
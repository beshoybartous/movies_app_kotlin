package com.example.moviesappmvpkotlin.ui.movie_detail

import com.example.moviesappmvpkotlin.base.BaseView

interface MovieDetailView:BaseView {
    fun isInserted(success: Boolean)
    fun isDeleted(success: Boolean)
}
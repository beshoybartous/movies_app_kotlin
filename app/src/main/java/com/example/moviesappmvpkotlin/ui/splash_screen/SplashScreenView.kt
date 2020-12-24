package com.example.moviesappmvpkotlin.ui.splash_screen

import com.example.moviesappmvpkotlin.base.BaseView

interface SplashScreenView :BaseView{
    fun getMoviesId(moviesIdList:List<Int>)
}
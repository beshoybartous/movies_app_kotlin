package com.example.moviesappmvpkotlin.ui.splash_screen

import android.os.Handler
import com.example.moviesappmvpkotlin.base.BaseActivity
import com.example.moviesappmvpkotlin.cache.SharedPref
import com.example.moviesappmvpkotlin.databinding.ActivitySplashBinding
import com.example.moviesappmvpkotlin.ui.movies.MoviesActivity

class SplashScreen : BaseActivity<SplashScreenPresenter, ActivitySplashBinding>(),SplashScreenView {
    override fun setViewBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun setPresenter(): SplashScreenPresenter {
       return SplashScreenPresenter(this, this)
    }

    override fun onPostCreated() {
        presenter.getData()
    }

    override fun getMoviesId(moviesIdList: List<Int>) {
        val handler = Handler()
        handler.postDelayed({
            SharedPref.setMoviesIDMap(moviesIdList)
            MoviesActivity.startMoviesActivity(this, moviesIdList)
            finish()
        }, 1)
    }
}
package com.example.moviesappmvpkotlin.ui.movies

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.moviesappmvpkotlin.R
import com.example.moviesappmvpkotlin.base.BaseActivity
import com.example.moviesappmvpkotlin.base.BaseView
import com.example.moviesappmvpkotlin.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MoviesActivity:BaseActivity<MoviesPresenter, ActivityMainBinding>(),BaseView {

    lateinit var moviesIDList: ArrayList<Int>
    companion object{
        private const val KEY_MOVIE_ID_LIST = "movies id"

        @JvmStatic
        fun startMoviesActivity(context: Context, moviesIdList: List<Int>) {
            val moviesActivityIntent = Intent(context, MoviesActivity::class.java)
            moviesActivityIntent.putIntegerArrayListExtra(
                KEY_MOVIE_ID_LIST,
                moviesIdList as ArrayList<Int?>?
            )
            context.startActivity(moviesActivityIntent)
        }
    }

    override fun setViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setPresenter(): MoviesPresenter {
        return MoviesPresenter(this, this)
    }

    override fun onPostCreated() {
     intent.getSerializableExtra(KEY_MOVIE_ID_LIST)?.let{
         moviesIDList = intent.getIntegerArrayListExtra(KEY_MOVIE_ID_LIST)!!
     }

        val navController = Navigation.findNavController(this, R.id.myNavHostFragment)
        NavigationUI.setupWithNavController(viewBinding.bottomNav, navController)
        viewBinding.bottomNav.background = null
        viewBinding.bottomNav.menu.getItem(1).isEnabled = false
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (
                destination.id == R.id.popular_movie_fragment ||
                destination.id == R.id.top_rated_movie_fragment
            ) {
                viewBinding.fabHome.backgroundTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            } else {
                viewBinding.fabHome.backgroundTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.colorPrimary))
            }
        }

        viewBinding.fabHome.setOnClickListener {
            navController.navigateUp()
            navController.navigate(R.id.favourite_movies_fragment)
        }
    }
}
package com.example.moviesappmvpkotlin.ui.movie_detail

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.moviesappmvpkotlin.R
import com.example.moviesappmvpkotlin.base.BaseActivity
import com.example.moviesappmvpkotlin.cache.SharedPref
import com.example.moviesappmvpkotlin.databinding.ActivityDetailBinding
import com.example.moviesappmvpkotlin.model.MovieModel
import com.example.moviesappmvpkotlin.model.eventbus.MovieEvent
import com.example.moviesappmvpkotlin.model.getPoster
import com.squareup.picasso.Picasso
import org.greenrobot.eventbus.EventBus

class MovieDetail :BaseActivity<MovieDetailPresenter, ActivityDetailBinding>(),MovieDetailView{
    private var isInDatabase=false
    lateinit  var movieModel: MovieModel

    override fun setViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }
    companion object{
        private const val KEY_MOVIE_DETAIL = "movie detail"

        @JvmStatic
        fun startMovieDetailActivity(context: Context, movie: MovieModel) {
            val moviesDetailActivityIntent = Intent(context, MovieDetail::class.java)
            moviesDetailActivityIntent.putExtra(
                KEY_MOVIE_DETAIL,
                movie
            )
            context.startActivity(moviesDetailActivityIntent)
        }
    }
    override fun setPresenter(): MovieDetailPresenter {
        return MovieDetailPresenter(this, this)
    }

    override fun onPostCreated() {
        intent.getParcelableExtra<MovieModel>(KEY_MOVIE_DETAIL)?.let{
            movieModel = intent.getParcelableExtra(KEY_MOVIE_DETAIL)!!
            viewBinding.tvMovieTitle.text=movieModel.title
            viewBinding.tvOverview.text=movieModel.overView
            viewBinding.tvReleaseDate.text=movieModel.releaseDate
            viewBinding.rbRating.rating = movieModel.voteAverage.toFloat()
            Picasso.get().load(movieModel.getPoster).into(viewBinding.ivMoviePoster)
            if (SharedPref.contain(movieModel.id!!)) {
                viewBinding.fabFavourite.setImageResource(R.drawable.ic_favorite)
                isInDatabase = true
            }
            viewBinding.fabFavourite.setOnClickListener {
                if (!isInDatabase) {
                    presenter.insertData(movieModel)
                } else {
                    presenter.deleteData(movieModel)
                }
            }
        }
    }

    override fun isInserted(success: Boolean) {
        if (success) {
            isInDatabase = true
            Log.d("inhere", "isInserted: before")
            viewBinding.fabFavourite.setImageResource(R.drawable.ic_favorite)
            Log.d("inhere", "isInserted: after")

            SharedPref.addValue(movieModel.id!!)
            EventBus.getDefault().postSticky(MovieEvent())
        } else {
            Toast.makeText(this, "Failed to save in database", Toast.LENGTH_SHORT).show()
        }
    }

    override fun isDeleted(success: Boolean) {
        if (success) {
            isInDatabase = false
            viewBinding.fabFavourite.setImageResource(R.drawable.ic_not_favourite)
            SharedPref.removeValue(movieModel.id!!)
            EventBus.getDefault().postSticky(MovieEvent())
        } else {
            Toast.makeText(this, "Failed to delete from database", Toast.LENGTH_SHORT).show()
        }
    }
}
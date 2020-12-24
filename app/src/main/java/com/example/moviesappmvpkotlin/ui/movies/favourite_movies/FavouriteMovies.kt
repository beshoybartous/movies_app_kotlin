package com.example.moviesappmvpkotlin.ui.movies.favourite_movies

import com.example.moviesappmvpkotlin.base.BaseFragment
import com.example.moviesappmvpkotlin.cache.SharedPref
import com.example.moviesappmvpkotlin.databinding.FragmentFavouriteMoviesBinding
import com.example.moviesappmvpkotlin.model.MovieModel
import com.example.moviesappmvpkotlin.model.eventbus.MovieEvent
import com.example.moviesappmvpkotlin.ui.movies.MovieClickListener
import com.example.moviesappmvpkotlin.ui.movies.MoviesAdapter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class FavouriteMovies : BaseFragment<FavouriteMoviesPresenter, FragmentFavouriteMoviesBinding>(),FavouriteMoviesView,MovieClickListener {

    lateinit var adapter: MoviesAdapter

    override fun setViewBinding(): FragmentFavouriteMoviesBinding {
        return FragmentFavouriteMoviesBinding.inflate(layoutInflater)
    }

    override fun setPresenter(): FavouriteMoviesPresenter {
        return FavouriteMoviesPresenter(this, requireContext())
    }

    override fun onPostCreated() {
        adapter = MoviesAdapter(this)
        viewBinding.rvFavouriteMovies.adapter = adapter
        presenter.getFavouriteMovies()
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun eventData(moviesIdEvent: MovieEvent?) {
        presenter.getFavouriteMovies()
    }
    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun getMovies(movies: List<MovieModel>) {
        movies.let {
            if(it.isNotEmpty()){
                adapter.updateList(it)
            }
        }
    }

    override fun isDeleted(id: Int?) {
        id?.let {
            adapter.remove(id)
            SharedPref.removeValue(id)
        }
    }

    override fun onCLick(movie: MovieModel?) {
        TODO("Not yet implemented")
    }

    override fun addToFavourite(movie: MovieModel?) {
        TODO("Not yet implemented")
    }

    override fun removeFromFavourite(model: MovieModel?) {
        TODO("Not yet implemented")
    }
}
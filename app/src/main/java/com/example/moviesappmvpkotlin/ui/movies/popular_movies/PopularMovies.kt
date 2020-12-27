package com.example.moviesappmvpkotlin.ui.movies.popular_movies

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesappmvpkotlin.base.BaseFragment
import com.example.moviesappmvpkotlin.cache.SharedPref
import com.example.moviesappmvpkotlin.databinding.FragmentPopularMoviesBinding
import com.example.moviesappmvpkotlin.model.MovieModel
import com.example.moviesappmvpkotlin.model.eventbus.MovieEvent
import com.example.moviesappmvpkotlin.model.payload.MoviePayLoad
import com.example.moviesappmvpkotlin.model.response.MovieResponse
import com.example.moviesappmvpkotlin.network.EndPoints
import com.example.moviesappmvpkotlin.ui.movie_detail.MovieDetail
import com.example.moviesappmvpkotlin.ui.movies.MovieClickListener
import com.example.moviesappmvpkotlin.ui.movies.MoviesAdapter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class PopularMovies : BaseFragment<PopularMoviesPresenter, FragmentPopularMoviesBinding>(),
    PopularMoviesView, MovieClickListener {
    lateinit var adapter: MoviesAdapter
    var pageCounter = 1
    var isLoading = true
    var totalNumberOfPages = 0
    override fun setViewBinding(): FragmentPopularMoviesBinding {
        return FragmentPopularMoviesBinding.inflate(layoutInflater)
    }

    override fun setPresenter(): PopularMoviesPresenter {
        return PopularMoviesPresenter(this, requireContext())
    }

    override fun onPostCreated() {
        adapter = MoviesAdapter(this)
        viewBinding.rvPopularMovies.adapter = adapter
        presenter.getData(MoviePayLoad(EndPoints.API_KEY, pageCounter))

        viewBinding.rvPopularMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val listSize = (recyclerView.layoutManager as LinearLayoutManager?)!!.itemCount - 2
                if (lastVisibleItemPosition >= listSize && isLoading && pageCounter < totalNumberOfPages) {
                    isLoading = false
                    pageCounter++
                    val moviesPayLoad2 = MoviePayLoad(EndPoints.API_KEY, pageCounter)
                    presenter.getData(moviesPayLoad2)
                }
            }
        })
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun eventData(movieEvent: MovieEvent?) {
        adapter.notifyDataSetChanged()
    }

    override fun getMovies(movieResponse: MovieResponse) {
        totalNumberOfPages = movieResponse.totalPages!!
        movieResponse.results?.let {
            if (it.isNotEmpty()) {
                adapter.updateList(movieResponse.results)
                isLoading = true
            }
        }
    }

    override fun isInserted(id: Int) {
        SharedPref.addValue(id)
        Log.d("isIndatabase", "bind: ${SharedPref.moviesIDMap.size}")

        adapter.notifyDataSetChanged()
    }

    override fun isDeleted(id: Int) {
        if(id!=-1) {
            SharedPref.removeValue(id)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCLick(movie: MovieModel?) {
        MovieDetail.startMovieDetailActivity(requireContext(), movie!!)

//        SharedPref.removeValue(id)
//        adapter.notifyDataSetChanged()
    }

    override fun addToFavourite(movie: MovieModel?) {
        movie?.let {
            presenter.insertData(movie)
        }
    }

    override fun removeFromFavourite(model: MovieModel?) {
        model?.let {
            presenter.deleteData(model)
        }
    }
}
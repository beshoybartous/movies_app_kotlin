package com.example.moviesappmvpkotlin.ui.movies

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesappmvpkotlin.R
import com.example.moviesappmvpkotlin.cache.SharedPref
import com.example.moviesappmvpkotlin.databinding.ItemMovieBinding
import com.example.moviesappmvpkotlin.model.MovieModel
import com.example.moviesappmvpkotlin.model.getPoster
import com.squareup.picasso.Picasso

public class MoviesAdapter( val movieClickListener: MovieClickListener) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {
     var moviesList = mutableListOf<MovieModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        return MovieViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(moviesList[position],movieClickListener)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    fun updateList(moviesList: List<MovieModel>) {
        this.moviesList = (this.moviesList + moviesList) as MutableList<MovieModel>
        notifyItemInserted(this.moviesList.size)
    }
    fun remove(id:Int){
        for (movie in moviesList){
            movie.id?.equals(id)?.let {
                moviesList.remove(movie)
                notifyDataSetChanged()
                return
            }
        }
    }

    class MovieViewHolder(private var binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var isInDatabase = false

        fun bind(movie: MovieModel,movieClickListener:MovieClickListener) {
            isInDatabase = SharedPref.contain(movie.id!!)
            Log.d("isIndatabase", "bind: ${SharedPref.moviesIDMap.size}")
            if (isInDatabase) {
                binding.btnFavourite.setImageResource(R.drawable.ic_favorite)
            } else {
                binding.btnFavourite.setImageResource(R.drawable.ic_not_favourite)
            }
            binding.btnFavourite.setOnClickListener {
                if (!isInDatabase) {
                    movieClickListener.addToFavourite(movie)
                } else {
                    movieClickListener.removeFromFavourite(movie)
                }
            }
            binding.root.setOnClickListener {
                movieClickListener.onCLick(movie)
            }


            Picasso.get().load(movie.getPoster)
                .into(binding.ivMoviePoster)

            binding.tvMovieTitle.text = (movie.title)
            binding.tvReleaseDate.text = (movie.releaseDate)
            binding.rbRating.rating = (movie.voteAverage.toFloat()) / 2
            //   binding.btnFavourite.setOnClickListener(this::onClick)
        }

        companion object {
            fun from(parent: ViewGroup): MovieViewHolder {
                return MovieViewHolder(
                    ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
        }

    }
}

interface MovieClickListener {
    fun onCLick(movie: MovieModel?)
    fun addToFavourite(movie: MovieModel?)
    fun removeFromFavourite(model: MovieModel?)
}
package com.example.moviesappmvpkotlin.ui.movies.favourite_movies

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesappmvpkotlin.R
import com.example.moviesappmvpkotlin.databinding.ItemMovieBinding
import com.example.moviesappmvpkotlin.model.MovieModel
import com.example.moviesappmvpkotlin.model.getPoster
import com.squareup.picasso.Picasso

class FavouriteMoviesAdapter :RecyclerView.Adapter<FavouriteMoviesAdapter.MovieViewHolder>(){

    var moviesList = listOf<MovieModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: FavouriteMoviesAdapter.MovieViewHolder, position: Int) {
        holder.bind(moviesList[position])
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }


    class MovieViewHolder(private var binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind(movie: MovieModel) {

            Log.d("tes1000000", movie.getPoster)
            binding.btnFavourite.setImageResource(R.drawable.ic_favorite)

            Picasso.get().load(movie.getPoster)
                .into(binding.ivMoviePoster)
            binding.tvMovieTitle.text = (movie.title)
            binding.tvReleaseDate.text = (movie.releaseDate)
            binding.rbRating.rating = (movie.voteAverage.toFloat()) / 2
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
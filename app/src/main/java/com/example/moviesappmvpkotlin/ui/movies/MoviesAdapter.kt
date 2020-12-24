package com.example.moviesappmvpkotlin.ui.movies

import android.util.Log
import android.view.LayoutInflater
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
        holder.bind(moviesList[position])
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
            }
        }
    }

    class MovieViewHolder(private var binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var isInDatabase = false

        fun bind(movie: MovieModel) {
            isInDatabase = SharedPref.contain(movie.id!!)
            if (isInDatabase) {
                binding.btnFavourite.setImageResource(R.drawable.ic_favorite)
            } else {
                binding.btnFavourite.setImageResource(R.drawable.ic_not_favourite)
            }
//            if (view.getId() == R.id.btn_favourite) {
//                if (!isInDatabase) {
//                    movieClickListener.addToFavourite(movieModel.get(position))
//                } else {
//                    movieClickListener.removeFromFavourite(movieModel.get(position))
//                }
//            } else {
//                movieClickListener.onCLick(movieModel.get(position))
//            }
            Log.d("tes1000000", movie.getPoster)

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
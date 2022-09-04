package com.ivan.garcia.sporaapplication.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ivan.garcia.sporaapplication.EXTRA_OBJECT
import com.ivan.garcia.sporaapplication.MovieDetailsActivity
import com.ivan.garcia.sporaapplication.R
import com.ivan.garcia.sporaapplication.databinding.MoviewItemLayoutOddBinding
import com.ivan.garcia.sporaapplication.databinding.MoviewItemLayoutPairBinding
import com.ivan.garcia.sporaapplication.models.Movie
import com.squareup.picasso.Picasso

class MoviesAdapter(private val moviesListener: MoviesListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var data = listOf<Movie>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private val pair = 0
    private val odd = 1

    private lateinit var context: Context

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = data[position]

        when (holder.itemViewType) {
            pair -> {
                val vhPair = holder as ViewHolderPair
                vhPair.bind(movie, position, context, moviesListener)
            }
            else -> {
                val vhOdd = holder as ViewHolderOdd
                vhOdd.bind(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context

        val viewHolder: RecyclerView.ViewHolder
        val inflater = LayoutInflater.from(parent.context)
        viewHolder = when (viewType) {
            pair -> {
                val bindingPair = MoviewItemLayoutPairBinding.inflate(inflater, parent, false)
                ViewHolderPair(bindingPair)
            }
            else -> {
                val bindingOdd = MoviewItemLayoutOddBinding.inflate(inflater, parent, false)
                ViewHolderOdd(bindingOdd)
            }
        }
        return viewHolder
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) {
            pair
        } else {
            odd
        }
    }
}

class ViewHolderOdd(private val binding: MoviewItemLayoutOddBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Movie) {
        binding.tvMovieTitle2.text = movie.title
        binding.tvMovieDirector2.text = movie.director
        binding.tvMovieCast2.text = movie.actors

        Picasso.get()
            .load(movie.posterUrl)
            .placeholder(R.drawable.movie_default)
            .into(binding.ivPoster2)
    }
}

class ViewHolderPair(private val binding: MoviewItemLayoutPairBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Movie, position: Int, context: Context, moviesListener: MoviesListener) {
        binding.tvMovieTitle.text = movie.title
        binding.tvMovieDirector.text = movie.director
        binding.tvMovieCast.text = movie.actors

        Picasso.get()
            .load(movie.posterUrl)
            .placeholder(R.drawable.movie_default)
            .into(binding.ivPoster)

        binding.root.setOnClickListener {
            val intent = Intent(context, MovieDetailsActivity::class.java).apply {
                putExtra(EXTRA_OBJECT, movie)
            }
            context.startActivity(intent)
        }

        binding.ivPoster.setOnClickListener { moviesListener.onPosterSelected(movie) }
        binding.btnViewDetails.setOnClickListener { moviesListener.onMovieSelected(movie) }
        binding.btnDelete.setOnClickListener { moviesListener.deleteMovie(position) }
    }
}

interface MoviesListener {
    fun onMovieSelected(movie: Movie)
    fun onPosterSelected(movie: Movie)
    fun deleteMovie(position: Int)
}
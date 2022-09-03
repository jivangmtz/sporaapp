package com.ivan.garcia.sporaapplication.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ivan.garcia.sporaapplication.EXTRA_OBJECT
import com.ivan.garcia.sporaapplication.MovieDetailsActivity
import com.ivan.garcia.sporaapplication.R
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
                configureViewHolderPair(vhPair, movie, position)
            }
            else -> {
                val vhOdd = holder as ViewHolderOdd
                configureViewHolderOdd(vhOdd, movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context

        val viewHolder: RecyclerView.ViewHolder
        val inflater = LayoutInflater.from(parent.context)
        viewHolder = when (viewType) {
            pair -> {
                val pairView: View = inflater.inflate(R.layout.moview_item_layout_pair, parent, false)
                ViewHolderPair(pairView)
            }
            else -> {
                val oddView: View = inflater.inflate(R.layout.moview_item_layout_odd, parent, false)
                ViewHolderOdd(oddView)
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

    private fun configureViewHolderPair(holder: ViewHolderPair, movie: Movie, position: Int) {
        holder.tvTitle.text = movie.title
        holder.tvDirector.text = movie.director
        holder.tvCast.text = movie.actors

        Picasso.get()
            .load(movie.posterUrl)
            .placeholder(R.drawable.movie_default)
            .into(holder.ivPoster)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, MovieDetailsActivity::class.java).apply {
                putExtra(EXTRA_OBJECT, movie)
            }
            context.startActivity(intent)
        }

        holder.ivPoster.setOnClickListener { moviesListener.onPosterSelected(movie) }
        holder.btnViewDetails.setOnClickListener { moviesListener.onMovieSelected(movie) }
        holder.btnDelete.setOnClickListener { moviesListener.deleteMovie(position) }
    }

    private fun configureViewHolderOdd(holder: ViewHolderOdd, movie: Movie) {
        holder.tvTitle.text = movie.title
        holder.tvDirector.text = movie.director
        holder.tvCast.text = movie.actors

        Picasso.get()
            .load(movie.posterUrl)
            .placeholder(R.drawable.movie_default)
            .into(holder.ivPoster)
    }
}

class ViewHolderOdd(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val ivPoster: ImageView = itemView.findViewById(R.id.ivPoster2)
    val tvTitle: TextView = itemView.findViewById(R.id.tvMovieTitle2)
    val tvDirector: TextView = itemView.findViewById(R.id.tvMovieDirector2)
    val tvCast: TextView = itemView.findViewById(R.id.tvMovieCast2)
}

class ViewHolderPair(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val ivPoster: ImageView = itemView.findViewById(R.id.ivPoster)
    val tvTitle: TextView = itemView.findViewById(R.id.tvMovieTitle)
    val tvDirector: TextView = itemView.findViewById(R.id.tvMovieDirector)
    val tvCast: TextView = itemView.findViewById(R.id.tvMovieCast)
    val btnViewDetails: Button = itemView.findViewById(R.id.btnViewDetails)
    val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)
}

interface MoviesListener {
    fun onMovieSelected(movie: Movie)
    fun onPosterSelected(movie: Movie)
    fun deleteMovie(position: Int)
}
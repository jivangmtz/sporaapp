package com.ivan.garcia.sporaapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.ivan.garcia.sporaapplication.adapters.MoviesAdapter
import com.ivan.garcia.sporaapplication.adapters.MoviesListener
import com.ivan.garcia.sporaapplication.databinding.ActivityMainBinding
import com.ivan.garcia.sporaapplication.models.Movie
import com.ivan.garcia.sporaapplication.viewmodel.MoviesViewModel
import com.ivan.garcia.sporaapplication.viewmodel.MoviesViewModelFactory

const val EXTRA_OBJECT = "moviemodel"

class MainActivity : AppCompatActivity(), MoviesListener {

    private lateinit var binding: ActivityMainBinding
    private var moviesAdapter: MoviesAdapter = MoviesAdapter(this)
    private val moviesViewModel: MoviesViewModel by viewModels {
        MoviesViewModelFactory(
            fileName = "movies.json",
            application = application
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            //btnGetMovies.setOnClickListener { moviesViewModel.getMovies() }
            rvMovies.adapter = moviesAdapter
            rvMovies.adapter = moviesAdapter
        }

        moviesViewModel.getMovies()
        moviesViewModel.movies.observe(this, Observer { moviesList ->
            moviesAdapter.data = moviesList
        })
    }

    override fun onMovieSelected(movie: Movie) {
        showMovieDetails(movie)
    }

    override fun onPosterSelected(movie: Movie) {
        Toast.makeText(applicationContext, movie.posterUrl, Toast.LENGTH_LONG).show()
    }

    override fun deleteMovie(position: Int) {
        moviesViewModel.removeMovieAt(position)
    }

    private fun showMovieDetails(movie: Movie) {
        val intent = Intent(this, MovieDetailsActivity::class.java).apply {
            putExtra(EXTRA_OBJECT, movie)
        }
        startActivity(intent)
    }
}
package com.ivan.garcia.sporaapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ivan.garcia.sporaapplication.adapters.MoviesAdapter
import com.ivan.garcia.sporaapplication.adapters.MoviesListener
import com.ivan.garcia.sporaapplication.databinding.ActivityMainBinding
import com.ivan.garcia.sporaapplication.models.Movie
import java.io.IOException

const val EXTRA_OBJECT = "moviemodel"

class MainActivity : AppCompatActivity(), MoviesListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var moviesList: ArrayList<Movie>
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadJsonDataFromAsset(applicationContext, "movies.json")

        moviesAdapter = MoviesAdapter(this)
        binding.rvMovies.adapter = moviesAdapter

        moviesAdapter.data = moviesList
    }

    private fun loadJsonDataFromAsset(context: Context, fileName: String) {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }

            val gson = Gson()
            val moviesListType = object : TypeToken<List<Movie>>() {}.type
            moviesList = gson.fromJson(jsonString, moviesListType)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
    }

    override fun onMovieSelected(movie: Movie) {
        showMovieDetails(movie)
    }

    override fun onPosterSelected(movie: Movie) {
        Toast.makeText(applicationContext, movie.posterUrl, Toast.LENGTH_LONG).show()
    }

    override fun deleteMovie(position: Int) {
        moviesList.removeAt(position)
        moviesAdapter.notifyItemRemoved(position)
    }

    private fun showMovieDetails(movie: Movie) {
        val intent = Intent(this, MovieDetailsActivity::class.java).apply {
            putExtra(EXTRA_OBJECT, movie)
        }
        startActivity(intent)
    }
}
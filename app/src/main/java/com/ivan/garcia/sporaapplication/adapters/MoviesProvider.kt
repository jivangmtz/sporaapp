package com.ivan.garcia.sporaapplication.adapters

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ivan.garcia.sporaapplication.models.Movie
import java.io.IOException

class MoviesProvider {
    companion object {
        fun getMovies(context: Context, fileName: String): ArrayList<Movie> {
            return loadJsonDataFromAsset(context, fileName)
        }

        private fun loadJsonDataFromAsset(context: Context, fileName: String): ArrayList<Movie> {
            val jsonString: String
            try {
                jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }

                val gson = Gson()
                val moviesListType = object : TypeToken<List<Movie>>() {}.type
                return gson.fromJson(jsonString, moviesListType)
            } catch (ioException: IOException) {
                ioException.printStackTrace()
            }
            return arrayListOf()
        }
    }
}
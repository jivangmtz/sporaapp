package com.ivan.garcia.sporaapplication.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.ivan.garcia.sporaapplication.adapters.MoviesProvider
import com.ivan.garcia.sporaapplication.models.Movie

class MoviesViewModel(application: Application, val fileName: String) :
    AndroidViewModel(application) {

    private val _movies = MutableLiveData<ArrayList<Movie>>()
    val movies: LiveData<ArrayList<Movie>> get() = _movies

    fun getMovies() {
        val moviesList = MoviesProvider.getMovies(
            getApplication<Application>().applicationContext,
            fileName
        )
        _movies.postValue(moviesList)
    }

    fun removeMovieAt(position: Int) {
        _movies.value?.removeAt(position)
        _movies.postValue(_movies.value)
    }
}

@Suppress("UNCHECKED_CAST")
class MoviesViewModelFactory(private val application: Application, private val fileName: String) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoviesViewModel(application = application, fileName = fileName) as T
    }
}
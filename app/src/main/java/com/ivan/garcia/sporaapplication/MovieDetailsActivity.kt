package com.ivan.garcia.sporaapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ivan.garcia.sporaapplication.databinding.ActivityMovieDetailsBinding
import com.ivan.garcia.sporaapplication.models.Movie

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val movie = intent.getSerializableExtra(EXTRA_OBJECT) as? Movie

        binding.tvDirector.text = movie?.director ?: ""
        binding.tvCast.text = movie?.actors ?: ""
        binding.tvSinopsis.text = movie?.plot ?: ""
    }
}
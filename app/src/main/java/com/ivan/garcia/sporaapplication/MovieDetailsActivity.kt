package com.ivan.garcia.sporaapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.ivan.garcia.sporaapplication.models.Movie

class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val movie = intent.getSerializableExtra(EXTRA_OBJECT) as? Movie

        findViewById<TextView>(R.id.tvDirector).apply {
            text = movie?.director ?: ""
        }

        findViewById<TextView>(R.id.tvCast).apply {
            text = movie?.actors ?: ""
        }

        findViewById<TextView>(R.id.tvSinopsis).apply {
            text = movie?.plot ?: ""
        }
    }
}
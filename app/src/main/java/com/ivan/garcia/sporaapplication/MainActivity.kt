package com.ivan.garcia.sporaapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ivan.garcia.sporaapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView4.setImageResource(R.drawable.android_studio)
        binding.imageView5.setImageResource(R.drawable.android_studio_svg)
        binding.imageView6.setImageResource(R.drawable.weather_icon)
    }
}
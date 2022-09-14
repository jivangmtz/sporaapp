package com.ivan.garcia.sporaapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ivan.garcia.sporaapplication.helpers.Validator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val number = 10
        println("$number is positive? ${Validator.isPositiveNumber(number)}")
    }
}
package com.ivan.garcia.sporaapplication.models

import java.io.Serializable

data class Movie(
    val id: Int,
    val title: String,
    val year: String,
    val runtime: String,
    val director: String,
    val actors: String,
    val plot: String,
    val posterUrl: String,
    val genres: List<String>
) : Serializable {
}
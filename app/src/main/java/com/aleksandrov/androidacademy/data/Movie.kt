package com.aleksandrov.androidacademy.data

data class Movie(
    val name: String,
    val pg: String,
    val isFollow: Boolean,
    val imageUrl: String,
    val tags: Collection<String>,
    val rating: Float,
    val reviewsCount: Int,
    val duration: Int
)
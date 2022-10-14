package com.example.applaudo.data.remote.dto

data class PopularMoviesDto(
    val page: Int,
    val results: List<MovieResult>,
    val total_pages: Int,
    val total_results: Int
)

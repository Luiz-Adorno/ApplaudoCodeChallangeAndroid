package com.example.applaudo.data.remote.dto

import com.example.applaudo.data.local.db.TopRatedMoviesCache
import com.google.gson.annotations.SerializedName

data class TopRatedMoviesDto(
    val page: Int,
    @SerializedName("results")
    val results: List<TopRatedMoviesCache>,
    val total_pages: Int,
    val total_results: Int
)

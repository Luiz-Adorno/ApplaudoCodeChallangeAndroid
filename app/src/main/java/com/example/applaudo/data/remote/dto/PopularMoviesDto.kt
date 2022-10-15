package com.example.applaudo.data.remote.dto

import com.example.applaudo.data.local.db.PopularMoviesCache
import com.google.gson.annotations.SerializedName

data class PopularMoviesDto(
    val page: Int,
    @SerializedName("results")
    val results: List<PopularMoviesCache>,
    val total_pages: Int,
    val total_results: Int
)

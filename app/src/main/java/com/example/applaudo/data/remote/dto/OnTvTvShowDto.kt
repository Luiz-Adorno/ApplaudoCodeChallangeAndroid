package com.example.applaudo.data.remote.dto

import com.example.applaudo.data.local.db.OnTvTvShowCache
import com.example.applaudo.data.local.db.TopRatedTvShowCache
import com.google.gson.annotations.SerializedName

data class OnTvTvShowDto(
    val page: Int,
    @SerializedName("results")
    val results: List<OnTvTvShowCache>,
    val total_pages: Int,
    val total_results: Int
)

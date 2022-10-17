package com.example.applaudo.data.remote.dto

import com.example.applaudo.data.local.db.PopularTvShowCache

data class PopularTvShowsDto(
    val page: Int,
    val results: List<PopularTvShowCache>,
    val total_pages: Int,
    val total_results: Int
)
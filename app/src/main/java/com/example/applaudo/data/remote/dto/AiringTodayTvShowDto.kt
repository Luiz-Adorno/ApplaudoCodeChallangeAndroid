package com.example.applaudo.data.remote.dto

import com.example.applaudo.data.local.db.AiringTodayTvShowCache

data class AiringTodayTvShowDto(
    val page: Int,
    val results: List<AiringTodayTvShowCache>,
    val total_pages: Int,
    val total_results: Int
)
package com.example.applaudo.domain

import com.example.applaudo.data.remote.dto.PopularMoviesDto

interface MoviesRepository {
    suspend fun getPopularMovies(page: Int, limit: Int): PopularMoviesDto

}
package com.example.applaudo.data.repository

import com.example.applaudo.data.remote.MovieDbApi
import com.example.applaudo.data.remote.dto.PopularMoviesDto
import com.example.applaudo.domain.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val api: MovieDbApi
) : MoviesRepository{
    override suspend fun getPopularMovies(page: Int, limit: Int): PopularMoviesDto {
        return api.getPopularMovies()
    }

}
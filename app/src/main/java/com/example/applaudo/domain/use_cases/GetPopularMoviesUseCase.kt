package com.example.applaudo.domain.use_cases

import com.example.applaudo.domain.MoviesRepository

class GetPopularMoviesUseCase(private val movieRepository: MoviesRepository) {
    operator fun invoke() = movieRepository.getPopularMovies()
}
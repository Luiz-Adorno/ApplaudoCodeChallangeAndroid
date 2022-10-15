package com.example.applaudo.domain.use_cases

import com.example.applaudo.domain.MoviesRepository

class GetTopRatedMoviesUseCase(private val movieRepository: MoviesRepository) {
    operator fun invoke() = movieRepository.getTopRatedMovies()
}
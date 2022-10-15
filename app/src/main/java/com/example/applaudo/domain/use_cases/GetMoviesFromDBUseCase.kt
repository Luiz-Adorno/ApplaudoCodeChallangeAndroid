package com.example.applaudo.domain.use_cases
import com.example.applaudo.domain.MoviesRepository


class GetMoviesFromDBUseCase(private val movieRepository: MoviesRepository) {
    operator fun invoke(movieID: Int) = movieRepository.getMoviesFromDB(movieID)
}
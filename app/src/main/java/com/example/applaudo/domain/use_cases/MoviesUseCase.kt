package com.example.applaudo.domain.use_cases

data class MoviesUseCase(
    val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    val getMoviesFromDBUseCase: GetMoviesFromDBUseCase,
    val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,

    )
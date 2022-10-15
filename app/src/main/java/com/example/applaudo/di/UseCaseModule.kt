package com.example.applaudo.di

import com.example.applaudo.domain.MoviesRepository
import com.example.applaudo.domain.use_cases.GetMoviesFromDBUseCase
import com.example.applaudo.domain.use_cases.GetPopularMoviesUseCase
import com.example.applaudo.domain.use_cases.GetTopRatedMoviesUseCase
import com.example.applaudo.domain.use_cases.MoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideMovieUseCases(movieRepository: MoviesRepository) = MoviesUseCase(
        getPopularMoviesUseCase = GetPopularMoviesUseCase(movieRepository = movieRepository),
        getMoviesFromDBUseCase = GetMoviesFromDBUseCase(movieRepository = movieRepository),
        getTopRatedMoviesUseCase = GetTopRatedMoviesUseCase(movieRepository = movieRepository)
    )
}
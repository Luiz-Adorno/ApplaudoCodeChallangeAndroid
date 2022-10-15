package com.example.applaudo.di

import com.example.applaudo.data.repository.MoviesRepositoryImpl
import com.example.applaudo.data.repository.dataSource.MovieLocalDataSource
import com.example.applaudo.data.repository.dataSource.MovieRemoteDataSource
import com.example.applaudo.domain.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideMoviesRepository(
        movieRemoteDataSource: MovieRemoteDataSource,
        movieLocalDataSource: MovieLocalDataSource
    ): MoviesRepository =
        MoviesRepositoryImpl(movieRemoteDataSource, movieLocalDataSource = movieLocalDataSource)
}
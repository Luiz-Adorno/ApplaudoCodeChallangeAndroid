package com.example.applaudo.data.repository

import com.example.applaudo.data.local.db.PopularMoviesCache
import com.example.applaudo.data.repository.dataSource.MovieLocalDataSource
import com.example.applaudo.data.repository.dataSource.MovieRemoteDataSource
import com.example.applaudo.domain.MoviesRepository
import kotlinx.coroutines.flow.Flow

class MoviesRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
) :
    MoviesRepository {
    override fun getPopularMovies() =
        movieRemoteDataSource.getPopularMovies()

    override fun getTopRatedMovies() =
        movieRemoteDataSource.getTopRatedMovies()

    override fun getMoviesFromDB(movieId: Int): Flow<PopularMoviesCache> =
        movieLocalDataSource.getMoviesFromDB(movieId)
}
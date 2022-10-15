package com.example.applaudo.data.repository.dataSourceImpl

import com.example.applaudo.data.local.db.MoviesDao
import com.example.applaudo.data.local.db.PopularMoviesCache
import com.example.applaudo.data.repository.dataSource.MovieLocalDataSource
import kotlinx.coroutines.flow.Flow


class MovieLocalDataSourceImpl(private val movieDao: MoviesDao) : MovieLocalDataSource {
    override fun getMoviesFromDB(movieId: Int): Flow<PopularMoviesCache> = movieDao.getMovie(movieId)
}
package com.example.applaudo.data.repository.dataSourceImpl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.applaudo.data.local.db.MovieDB
import com.example.applaudo.data.local.db.PopularMoviesCache
import com.example.applaudo.data.local.db.TopRatedMoviesCache
import com.example.applaudo.data.local.paging.PopularMovieRemoteMediator
import com.example.applaudo.data.local.paging.TopRatedMovieRemoteMediator
import com.example.applaudo.data.remote.MovieDbApi
import com.example.applaudo.data.repository.dataSource.MovieRemoteDataSource
import kotlinx.coroutines.flow.Flow


class MovieRemoteDataSourceImpl(private val movieApi: MovieDbApi, private val movieDB: MovieDB) :
    MovieRemoteDataSource {
    private val movieDao = movieDB.movieDao()

    @OptIn(ExperimentalPagingApi::class)
    override  fun getPopularMovies() : Flow<PagingData<PopularMoviesCache>> {
        val pagingSourceFactory = { movieDao.getPopularMovies() }
        return Pager(
            config = PagingConfig(pageSize = 30),
            remoteMediator = PopularMovieRemoteMediator(
                movieApi,
                movieDB
            ),
            pagingSourceFactory = pagingSourceFactory,
        ).flow
    }

    @OptIn(ExperimentalPagingApi::class)
    override  fun getTopRatedMovies() : Flow<PagingData<TopRatedMoviesCache>> {
        val pagingSourceFactory = { movieDao.getTopRatedMovies() }
        return Pager(
            config = PagingConfig(pageSize = 30),
            remoteMediator = TopRatedMovieRemoteMediator(
                movieApi,
                movieDB
            ),
            pagingSourceFactory = pagingSourceFactory,
        ).flow
    }
}
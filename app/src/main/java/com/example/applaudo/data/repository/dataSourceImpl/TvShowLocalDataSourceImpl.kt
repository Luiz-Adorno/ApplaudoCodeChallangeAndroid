package com.example.applaudo.data.repository.dataSourceImpl

import com.example.applaudo.data.local.db.*
import com.example.applaudo.data.repository.dataSource.TvShowLocalDataSource
import kotlinx.coroutines.flow.Flow


class TvShowLocalDataSourceImpl(private val tvShowsDao: TvShowsDao) : TvShowLocalDataSource {
    override fun getPopularTvShowsFromDB(movieId: Int): Flow<PopularTvShowCache> =
        tvShowsDao.getPopularTvShow(movieId)
    override fun getTopRatedMoviesFromDB(movieId: Int): Flow<TopRatedTvShowCache> =
        tvShowsDao.getTopRatedTvShow(movieId)
    override fun getOnTvTvShowsFromDB(movieId: Int): Flow<OnTvTvShowCache> =
        tvShowsDao.getOnTvTvShow(movieId)
    override fun getAiringTodayTvShowsFromDB(movieId: Int): Flow<AiringTodayTvShowCache> =
        tvShowsDao.getAiringTodayTvShow(movieId)

}
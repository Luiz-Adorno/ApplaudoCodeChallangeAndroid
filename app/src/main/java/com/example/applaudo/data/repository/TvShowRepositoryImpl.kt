package com.example.applaudo.data.repository

import androidx.paging.PagingData
import com.example.applaudo.data.local.db.AiringTodayTvShowCache
import com.example.applaudo.data.local.db.OnTvTvShowCache
import com.example.applaudo.data.local.db.PopularTvShowCache
import com.example.applaudo.data.local.db.TopRatedTvShowCache
import com.example.applaudo.data.repository.dataSource.TvShowLocalDataSource
import com.example.applaudo.data.repository.dataSource.TvShowRemoteDataSource
import com.example.applaudo.domain.TvShowRepository
import kotlinx.coroutines.flow.Flow

class TvShowRepositoryImpl(
    private val tvShowRemoteDataSource: TvShowRemoteDataSource,
    private val tvShowLocalDataSource: TvShowLocalDataSource,
) :
    TvShowRepository {
    override fun getPopularTvShow() =
        tvShowRemoteDataSource.getPopularTvShows()

    override fun getTopRatedTvShow() =
        tvShowRemoteDataSource.getTopRatedTvShows()

    override fun getOnTvTvShow() =
        tvShowRemoteDataSource.getOnTvTvShows()

    override fun getAiringTodayTvShow(): Flow<PagingData<AiringTodayTvShowCache>> =
        tvShowRemoteDataSource.getAiringTodayTvShows()

    override fun getOnTvTvTvShowsFromDB(movieId: Int): Flow<OnTvTvShowCache> =
        tvShowLocalDataSource.getOnTvTvShowsFromDB(movieId)

    override fun getPopularTvShowsFromDB(movieId: Int): Flow<PopularTvShowCache> =
        tvShowLocalDataSource.getPopularTvShowsFromDB(movieId)

    override fun getTopRatedMoviesFromDB(movieId: Int): Flow<TopRatedTvShowCache> =
        tvShowLocalDataSource.getTopRatedMoviesFromDB(movieId)

    override fun getAiringTodayTvShowsFromDB(movieId: Int): Flow<AiringTodayTvShowCache> =
        tvShowLocalDataSource.getAiringTodayTvShowsFromDB(movieId)
}
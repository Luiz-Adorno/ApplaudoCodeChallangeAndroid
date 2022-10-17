package com.example.applaudo.data.repository.dataSourceImpl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.applaudo.data.local.db.*
import com.example.applaudo.data.local.paging.AiringTodayTvShowsRemoteMediator
import com.example.applaudo.data.local.paging.OnTvTvShowsRemoteMediator
import com.example.applaudo.data.local.paging.PopularTvShowsRemoteMediator
import com.example.applaudo.data.local.paging.TopRatedTvShowsRemoteMediator
import com.example.applaudo.data.remote.TvShowsApi
import com.example.applaudo.data.repository.dataSource.TvShowRemoteDataSource
import kotlinx.coroutines.flow.Flow


class TvShowRemoteDataSourceImpl(private val tvShowApi: TvShowsApi, private val tvShowDB: TvShowDB) :
    TvShowRemoteDataSource {
    private val tvShowDao = tvShowDB.tvShowsDao()

    @OptIn(ExperimentalPagingApi::class)
    override fun getPopularTvShows() : Flow<PagingData<PopularTvShowCache>> {
        val pagingSourceFactory = { tvShowDao.getPopularTvShows() }
        return Pager(
            config = PagingConfig(pageSize = 30),
            remoteMediator = PopularTvShowsRemoteMediator(
                tvShowApi,
                tvShowDB
            ),
            pagingSourceFactory = pagingSourceFactory,
        ).flow
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getTopRatedTvShows() : Flow<PagingData<TopRatedTvShowCache>> {
        val pagingSourceFactory = { tvShowDao.getTopRatedTvShows() }
        return Pager(
            config = PagingConfig(pageSize = 30),
            remoteMediator = TopRatedTvShowsRemoteMediator(
                tvShowApi,
                tvShowDB
            ),
            pagingSourceFactory = pagingSourceFactory,
        ).flow
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getOnTvTvShows(): Flow<PagingData<OnTvTvShowCache>> {
        val pagingSourceFactory = { tvShowDao.getOnTvTvShows() }
        return Pager(
            config = PagingConfig(pageSize = 30),
            remoteMediator = OnTvTvShowsRemoteMediator(
                tvShowApi,
                tvShowDB
            ),
            pagingSourceFactory = pagingSourceFactory,
        ).flow
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getAiringTodayTvShows(): Flow<PagingData<AiringTodayTvShowCache>> {
        val pagingSourceFactory = { tvShowDao.getAiringTodayTvShows() }
        return Pager(
            config = PagingConfig(pageSize = 30),
            remoteMediator = AiringTodayTvShowsRemoteMediator(
                tvShowApi,
                tvShowDB
            ),
            pagingSourceFactory = pagingSourceFactory,
        ).flow
    }
}
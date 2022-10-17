package com.example.applaudo.data.repository.dataSource

import androidx.paging.PagingData
import com.example.applaudo.data.local.db.AiringTodayTvShowCache
import com.example.applaudo.data.local.db.OnTvTvShowCache
import com.example.applaudo.data.local.db.PopularTvShowCache
import com.example.applaudo.data.local.db.TopRatedTvShowCache
import kotlinx.coroutines.flow.Flow

interface TvShowRemoteDataSource {
     fun getPopularTvShows(): Flow<PagingData<PopularTvShowCache>>
     fun getTopRatedTvShows(): Flow<PagingData<TopRatedTvShowCache>>
     fun getOnTvTvShows(): Flow<PagingData<OnTvTvShowCache>>
     fun getAiringTodayTvShows(): Flow<PagingData<AiringTodayTvShowCache>>

}
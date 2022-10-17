package com.example.applaudo.domain

import androidx.paging.PagingData
import com.example.applaudo.data.local.db.AiringTodayTvShowCache
import com.example.applaudo.data.local.db.OnTvTvShowCache
import com.example.applaudo.data.local.db.PopularTvShowCache
import com.example.applaudo.data.local.db.TopRatedTvShowCache
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {
    fun getPopularTvShow(): Flow<PagingData<PopularTvShowCache>>
    fun getTopRatedTvShow(): Flow<PagingData<TopRatedTvShowCache>>
    fun getOnTvTvShow(): Flow<PagingData<OnTvTvShowCache>>
    fun getAiringTodayTvShow(): Flow<PagingData<AiringTodayTvShowCache>>
    fun getAiringTodayTvShowsFromDB(movieId: Int): Flow<AiringTodayTvShowCache>
    fun getPopularTvShowsFromDB(movieId: Int): Flow<PopularTvShowCache>
    fun getTopRatedMoviesFromDB(movieId: Int): Flow<TopRatedTvShowCache>
    fun getOnTvTvTvShowsFromDB(movieId: Int): Flow<OnTvTvShowCache>
}
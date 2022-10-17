package com.example.applaudo.data.repository.dataSource
import com.example.applaudo.data.local.db.AiringTodayTvShowCache
import com.example.applaudo.data.local.db.OnTvTvShowCache
import com.example.applaudo.data.local.db.PopularTvShowCache
import com.example.applaudo.data.local.db.TopRatedTvShowCache
import kotlinx.coroutines.flow.Flow

interface TvShowLocalDataSource {
    fun getPopularTvShowsFromDB(movieId : Int): Flow<PopularTvShowCache>
    fun getTopRatedMoviesFromDB(movieId : Int): Flow<TopRatedTvShowCache>
    fun getOnTvTvShowsFromDB(movieId : Int): Flow<OnTvTvShowCache>
    fun getAiringTodayTvShowsFromDB(movieId : Int): Flow<AiringTodayTvShowCache>
}
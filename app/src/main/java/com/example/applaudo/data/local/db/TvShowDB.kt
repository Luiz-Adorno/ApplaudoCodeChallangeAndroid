package com.example.applaudo.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.applaudo.data.local.paging.TvShowsRemoteKeysDao
import com.example.applaudo.domain.model.AiringTodayTvShowRemoteKeys
import com.example.applaudo.domain.model.OnTvTvShowRemoteKeys
import com.example.applaudo.domain.model.PopularTvShowRemoteKeys
import com.example.applaudo.domain.model.TopRatedTvShowRemoteKeys

@Database(
    entities = [PopularTvShowCache::class,
        OnTvTvShowCache::class,
        TopRatedTvShowCache::class,
        AiringTodayTvShowCache::class,
        PopularTvShowRemoteKeys::class,
        TopRatedTvShowRemoteKeys::class,
        OnTvTvShowRemoteKeys::class,
        AiringTodayTvShowRemoteKeys::class],
    version = 9,
    exportSchema = false
)
abstract class TvShowDB : RoomDatabase() {
    abstract fun tvShowsDao(): TvShowsDao
    abstract fun tvShowsRemoteKeysDao(): TvShowsRemoteKeysDao
}
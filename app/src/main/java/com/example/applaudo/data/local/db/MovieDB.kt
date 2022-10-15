package com.example.applaudo.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.applaudo.data.local.paging.MovieRemoteKeysDao
import com.example.applaudo.domain.model.MovieRemoteKeys

@Database(
    entities = [PopularMoviesCache::class, MovieRemoteKeys::class, TopRatedMoviesCache::class],
    version = 3,
    exportSchema = false
)
abstract class MovieDB : RoomDatabase() {
    abstract fun movieDao(): MoviesDao
    abstract fun movieRemoteKeysDao(): MovieRemoteKeysDao
}
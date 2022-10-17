package com.example.applaudo.di

import android.app.Application
import androidx.room.Room
import com.example.applaudo.data.local.db.TvShowDB
import com.example.applaudo.data.local.db.TvShowsDao
import com.example.applaudo.data.local.paging.TvShowsRemoteKeysDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(app: Application): TvShowDB =
        Room.databaseBuilder(app, TvShowDB::class.java, "popularMovies").fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideMovieDao(tvShowDB: TvShowDB) : TvShowsDao = tvShowDB.tvShowsDao()

    @Provides
    fun provideMovieRemoteKeysDao(tvShowDB: TvShowDB) : TvShowsRemoteKeysDao = tvShowDB.tvShowsRemoteKeysDao()
}
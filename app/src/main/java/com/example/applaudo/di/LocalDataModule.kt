package com.example.applaudo.di

import com.example.applaudo.data.local.db.TvShowsDao
import com.example.applaudo.data.repository.dataSource.TvShowLocalDataSource
import com.example.applaudo.data.repository.dataSourceImpl.TvShowLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {
    @Provides
    fun provideLocalDataSource(movieDao: TvShowsDao): TvShowLocalDataSource =
        TvShowLocalDataSourceImpl(tvShowsDao = movieDao)
}
package com.example.applaudo.di

import com.example.applaudo.data.local.db.MoviesDao
import com.example.applaudo.data.repository.dataSource.MovieLocalDataSource
import com.example.applaudo.data.repository.dataSourceImpl.MovieLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {
    @Provides
    fun provideLocalDataSource(movieDao: MoviesDao): MovieLocalDataSource =
        MovieLocalDataSourceImpl(movieDao = movieDao)
}
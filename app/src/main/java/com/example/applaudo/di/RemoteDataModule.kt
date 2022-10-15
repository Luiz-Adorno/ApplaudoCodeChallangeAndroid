package com.example.applaudo.di

import com.example.applaudo.data.local.db.MovieDB
import com.example.applaudo.data.remote.MovieDbApi
import com.example.applaudo.data.repository.dataSource.MovieRemoteDataSource
import com.example.applaudo.data.repository.dataSourceImpl.MovieRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {
    @Provides
    fun provideMoviesRemoteDataSource(movieDbApi: MovieDbApi, movieDB: MovieDB) : MovieRemoteDataSource =
        MovieRemoteDataSourceImpl(movieDbApi, movieDB = movieDB)
}
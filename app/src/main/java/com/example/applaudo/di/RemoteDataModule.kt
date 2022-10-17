package com.example.applaudo.di

import com.example.applaudo.data.local.db.TvShowDB
import com.example.applaudo.data.remote.TvShowsApi
import com.example.applaudo.data.repository.dataSource.TvShowRemoteDataSource
import com.example.applaudo.data.repository.dataSourceImpl.TvShowRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {
    @Provides
    fun provideTvShowRemoteDataSource(tvShowsApi: TvShowsApi, tvShowDB: TvShowDB) : TvShowRemoteDataSource =
        TvShowRemoteDataSourceImpl(tvShowsApi, tvShowDB = tvShowDB)
}

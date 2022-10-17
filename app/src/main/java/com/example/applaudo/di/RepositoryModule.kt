package com.example.applaudo.di

import com.example.applaudo.data.repository.TvShowRepositoryImpl
import com.example.applaudo.data.repository.dataSource.TvShowLocalDataSource
import com.example.applaudo.data.repository.dataSource.TvShowRemoteDataSource
import com.example.applaudo.domain.TvShowRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideMoviesRepository(
        tvShowRemoteDataSource: TvShowRemoteDataSource,
        tvShowLocalDataSource: TvShowLocalDataSource
    ): TvShowRepository =
        TvShowRepositoryImpl(tvShowRemoteDataSource, tvShowLocalDataSource = tvShowLocalDataSource)
}
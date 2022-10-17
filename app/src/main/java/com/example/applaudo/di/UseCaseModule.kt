package com.example.applaudo.di

import com.example.applaudo.domain.TvShowRepository
import com.example.applaudo.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideTvShowUseCases(movieRepository: TvShowRepository) = TvShowUseCase(
        getPopularTvShowsUseCase = GetPopularTvShowsUseCase(tvShowRepository = movieRepository),
        getPopularTvShowsFromDBUseCase = GetPopularTvShowsFromDBUseCase(tvShowRepository = movieRepository),
        getTopRatedTvShowsUseCase = GetTopRatedTvShowsUseCase(tvShowRepository = movieRepository),
        getTopRatedTvShowsFromDbUseCase = GetTopRatedTvShowsFromDbUseCase(tvShowRepository = movieRepository),
        getOnTvTvShowsFromDbUseCase = GetOnTvTvShowsFromDBUseCase(tvShowRepository = movieRepository),
        getOnTvTvShowsUseCase = GetOnTvTvShowsUseCase(tvShowRepository = movieRepository),
        getAiringTodayTvShowCache = GetAiringTodayTvShowsUseCase(tvShowRepository = movieRepository),
        getAiringTodayTvShowFromDBUseCase = GetAiringTodayTvShowsFromDBUseCase(tvShowRepository = movieRepository)

    )
}
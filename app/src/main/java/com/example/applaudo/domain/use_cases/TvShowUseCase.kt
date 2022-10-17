package com.example.applaudo.domain.use_cases

data class TvShowUseCase(
    val getPopularTvShowsUseCase: GetPopularTvShowsUseCase,
    val getPopularTvShowsFromDBUseCase: GetPopularTvShowsFromDBUseCase,
    val getTopRatedTvShowsUseCase: GetTopRatedTvShowsUseCase,
    val getTopRatedTvShowsFromDbUseCase: GetTopRatedTvShowsFromDbUseCase,
    val getOnTvTvShowsUseCase: GetOnTvTvShowsUseCase,
    val getOnTvTvShowsFromDbUseCase: GetOnTvTvShowsFromDBUseCase,
    val getAiringTodayTvShowCache: GetAiringTodayTvShowsUseCase,
    val getAiringTodayTvShowFromDBUseCase: GetAiringTodayTvShowsFromDBUseCase
    )
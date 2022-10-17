package com.example.applaudo.domain.use_cases

import com.example.applaudo.domain.TvShowRepository

class GetAiringTodayTvShowsUseCase(private val tvShowRepository: TvShowRepository) {
    operator fun invoke() = tvShowRepository.getAiringTodayTvShow()
}
package com.example.applaudo.domain.use_cases

import com.example.applaudo.domain.TvShowRepository

class GetOnTvTvShowsUseCase(private val tvShowRepository: TvShowRepository) {
    operator fun invoke() = tvShowRepository.getOnTvTvShow()
}
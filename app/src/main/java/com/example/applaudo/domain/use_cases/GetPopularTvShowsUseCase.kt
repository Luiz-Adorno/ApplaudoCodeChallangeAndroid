package com.example.applaudo.domain.use_cases

import com.example.applaudo.domain.TvShowRepository

class GetPopularTvShowsUseCase(private val tvShowRepository: TvShowRepository) {
    operator fun invoke() = tvShowRepository.getPopularTvShow()
}
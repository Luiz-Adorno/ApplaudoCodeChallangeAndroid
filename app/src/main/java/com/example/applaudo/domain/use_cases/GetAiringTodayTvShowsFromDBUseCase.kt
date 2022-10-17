package com.example.applaudo.domain.use_cases
import com.example.applaudo.domain.TvShowRepository


class GetAiringTodayTvShowsFromDBUseCase(private val tvShowRepository: TvShowRepository) {
    operator fun invoke(movieID: Int) = tvShowRepository.getAiringTodayTvShowsFromDB(movieID)
}
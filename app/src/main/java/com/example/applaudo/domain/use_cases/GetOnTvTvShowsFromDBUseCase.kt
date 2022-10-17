package com.example.applaudo.domain.use_cases
import com.example.applaudo.domain.TvShowRepository


class GetOnTvTvShowsFromDBUseCase(private val tvShowRepository: TvShowRepository) {
    operator fun invoke(movieID: Int) = tvShowRepository.getOnTvTvTvShowsFromDB(movieID)
}
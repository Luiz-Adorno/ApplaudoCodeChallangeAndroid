package com.example.applaudo.domain.use_cases
import com.example.applaudo.domain.TvShowRepository


class GetPopularTvShowsFromDBUseCase(private val tvShowRepository: TvShowRepository) {
    operator fun invoke(movieID: Int) = tvShowRepository.getPopularTvShowsFromDB(movieID)
}
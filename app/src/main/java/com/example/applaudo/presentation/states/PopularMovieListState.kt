package com.example.applaudo.presentation.states

import com.example.applaudo.data.remote.dto.MovieResult

data class PopularMovieListState(
    val isLoading: Boolean = false,
    val popularMovies: List<MovieResult> = emptyList(),
    val error: String = ""
)
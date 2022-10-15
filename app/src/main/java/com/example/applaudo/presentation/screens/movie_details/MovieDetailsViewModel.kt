package com.example.applaudo.presentation.screens.movie_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applaudo.data.local.db.PopularMoviesCache
import com.example.applaudo.domain.use_cases.MoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieUseCases: MoviesUseCase
) : ViewModel() {
    private val _selectedMovie: MutableStateFlow<PopularMoviesCache?> = MutableStateFlow(null)
    val selectedMovie: StateFlow<PopularMoviesCache?> = _selectedMovie

    fun getMovieDetails(movieID: Int) {
        viewModelScope.launch {
            movieUseCases.getMoviesFromDBUseCase.invoke(movieID = movieID).collect {
                _selectedMovie.value = it
            }
        }
    }
}
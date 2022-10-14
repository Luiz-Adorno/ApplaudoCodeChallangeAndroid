package com.example.applaudo.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applaudo.common.Resource
import com.example.applaudo.domain.use_cases.GetPopularMoviesUseCase
import com.example.applaudo.presentation.states.PopularMovieListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val popularMoviesUseCases: GetPopularMoviesUseCase
) : ViewModel() {
    private var getMoviesJob: Job? = null
    private val _state = mutableStateOf(PopularMovieListState())
    val popularMovieEstate: State<PopularMovieListState> = _state
    var selectedType: MutableLiveData<String> = MutableLiveData("Popular")

    init {
        getPopularMovies()
        selectedType.value
    }

    private fun getPopularMovies() {
        getMoviesJob?.cancel()
        getMoviesJob = popularMoviesUseCases.invoke().onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value =
                        PopularMovieListState(error = result.message ?: "An unexpected error occured")
                }
                is Resource.Loading -> {
                    _state.value = PopularMovieListState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = PopularMovieListState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }
}
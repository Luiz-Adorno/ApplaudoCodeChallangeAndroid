package com.example.applaudo.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.applaudo.domain.use_cases.MoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    movieUseCases: MoviesUseCase,
) : ViewModel() {
    val getAllPopularMovies = movieUseCases.getPopularMoviesUseCase()
    val getTopRatedMovies = movieUseCases.getTopRatedMoviesUseCase()
    var selectedType: MutableLiveData<String> = MutableLiveData("Popular")


}
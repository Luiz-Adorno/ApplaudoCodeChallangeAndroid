package com.example.applaudo.presentation.screens.tv_show_details.top_rated

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applaudo.data.local.db.TopRatedTvShowCache
import com.example.applaudo.domain.use_cases.TvShowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopRatedTvShowDetailsViewModel @Inject constructor(
    private val tvShowUseCase: TvShowUseCase
) : ViewModel() {
    private val _selectedTvShow: MutableStateFlow<TopRatedTvShowCache?> = MutableStateFlow(null)
    val selectedTvShow: StateFlow<TopRatedTvShowCache?> = _selectedTvShow

    fun getTvShowDetails(movieID: Int) {
        viewModelScope.launch {
            tvShowUseCase.getTopRatedTvShowsFromDbUseCase.invoke(movieID = movieID).collect {
                _selectedTvShow.value = it
            }
        }
    }
}
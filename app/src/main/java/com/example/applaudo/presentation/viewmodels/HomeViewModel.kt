package com.example.applaudo.presentation.viewmodels

import androidx.compose.runtime.Composable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.applaudo.domain.use_cases.TvShowUseCase
import com.example.applaudo.presentation.screens.AppBar
import com.example.applaudo.presentation.screens.PageContent
import com.example.applaudo.presentation.screens.lists.AiringTodayShowListContent
import com.example.applaudo.presentation.screens.lists.OnTvTvShowListContent
import com.example.applaudo.presentation.screens.lists.PopularMovieListContent
import com.example.applaudo.presentation.screens.lists.TopRatedMovieListContent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    movieUseCases: TvShowUseCase,
) : ViewModel() {
    private val getAllPopularMovies = movieUseCases.getPopularTvShowsUseCase()
    private val getTopRatedMovies = movieUseCases.getTopRatedTvShowsUseCase()
    private val onTvTvShowMovies = movieUseCases.getOnTvTvShowsUseCase()
    private val airingTodayTvShowMovies = movieUseCases.getAiringTodayTvShowCache()
    var selectedType: MutableLiveData<String> = MutableLiveData("Popular")

    @Composable
    fun Refresh(
                navController: NavController
    ){
        when(selectedType.value){
            "Popular" -> {
                PageContent(navController, this)
                PopularMovieListContent(getAllPopularMovies.collectAsLazyPagingItems(),navController)
            }
            "Top Rated" -> {
                PageContent(navController, this)
                TopRatedMovieListContent(getTopRatedMovies.collectAsLazyPagingItems(),navController)
            }
            "On TV" -> {
                PageContent(navController, this)
                OnTvTvShowListContent(onTvTvShowMovies.collectAsLazyPagingItems(),navController)
            }
            "Airing Today" -> {
                PageContent(navController, this)
                AiringTodayShowListContent(airingTodayTvShowMovies.collectAsLazyPagingItems(),navController)
            }
        }

    }

}
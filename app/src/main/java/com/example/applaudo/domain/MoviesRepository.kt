package com.example.applaudo.domain

import androidx.paging.PagingData
import com.example.applaudo.data.local.db.PopularMoviesCache
import com.example.applaudo.data.local.db.TopRatedMoviesCache
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getPopularMovies(): Flow<PagingData<PopularMoviesCache>>
    fun getTopRatedMovies(): Flow<PagingData<TopRatedMoviesCache>>
    fun getMoviesFromDB(movieId: Int): Flow<PopularMoviesCache>

}
package com.example.applaudo.data.repository.dataSource

import androidx.paging.PagingData
import com.example.applaudo.data.local.db.PopularMoviesCache
import com.example.applaudo.data.local.db.TopRatedMoviesCache
import kotlinx.coroutines.flow.Flow

interface MovieRemoteDataSource {
     fun getPopularMovies(): Flow<PagingData<PopularMoviesCache>>
     fun getTopRatedMovies(): Flow<PagingData<TopRatedMoviesCache>>
}
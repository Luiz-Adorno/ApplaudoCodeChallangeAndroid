package com.example.applaudo.data.repository.dataSource
import com.example.applaudo.data.local.db.PopularMoviesCache
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    fun getMoviesFromDB(movieId : Int): Flow<PopularMoviesCache>
}
package com.example.applaudo.data.local.db

import com.example.applaudo.data.remote.dto.MovieResult
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    fun getMoviesFromDB(movieId : Int): Flow<MovieResult>
}
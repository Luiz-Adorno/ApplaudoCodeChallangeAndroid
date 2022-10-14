package com.example.applaudo.data.local.db

import androidx.paging.PagingSource
import androidx.room.*
import com.example.applaudo.data.remote.dto.MovieResult

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPopularMovies(popularMovies: List<MovieResult>)

    @Query("SELECT * FROM popularMovies")
    fun getPopularMovies(): PagingSource<Int, MovieResult>
}

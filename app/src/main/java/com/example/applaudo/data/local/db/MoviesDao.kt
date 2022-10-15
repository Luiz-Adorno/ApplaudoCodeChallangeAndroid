package com.example.applaudo.data.local.db

import androidx.paging.PagingSource
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPopularMovies(popularMovies: List<PopularMoviesCache>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTopRatedMovies(topRatedMovies: List<TopRatedMoviesCache>)

    @Query("SELECT * FROM popularMovies")
    fun getPopularMovies(): PagingSource<Int, PopularMoviesCache>

    @Query("SELECT * FROM topRatedMovies")
    fun getTopRatedMovies(): PagingSource<Int, TopRatedMoviesCache>

    @Query("SELECT * FROM popularMovies WHERE id = :id")
    fun getMovie(id: Int): Flow<PopularMoviesCache>

    @Query("DELETE FROM popularMovies")
    suspend fun deleteAllMovies()

    @Query("DELETE FROM topRatedMovies")
    suspend fun deleteAllTopRatedMovies()
}

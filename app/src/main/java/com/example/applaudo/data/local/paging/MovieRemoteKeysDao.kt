package com.example.applaudo.data.local.paging

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.applaudo.data.remote.dto.MovieResult

@Dao
interface MovieRemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPopularMoviesRemote(popularMovies : List<MovieResult>)

    @Query("SELECT * FROM popularMovies")
    suspend fun getMovieRemoteKeys(): List<MovieResult>



}
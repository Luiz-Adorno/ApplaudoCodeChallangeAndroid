package com.example.applaudo.data.local.paging

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.applaudo.domain.model.MovieRemoteKeys

@Dao
interface MovieRemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPopularMoviesRemote(popularMovies : List<MovieRemoteKeys>)

    @Query("SELECT * FROM movie_remote_keys WHERE id = :id")
    suspend fun getMovieRemoteKeys(id: Int): MovieRemoteKeys?

    @Query("DELETE FROM movie_remote_keys")
    suspend fun deleteAllMovieRemoteKeys()

}
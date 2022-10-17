package com.example.applaudo.data.local.db

import androidx.paging.PagingSource
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TvShowsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPopularTvShows(popularTvShow: List<PopularTvShowCache>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTopRatedTvShows(topRatedTvShow: List<TopRatedTvShowCache>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOnTvRatedTvShows(onTvTvShow: List<OnTvTvShowCache>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAiringTodayTvShows(airingTodayTvShow: List<AiringTodayTvShowCache>)

    @Query("SELECT * FROM popularTvShow")
    fun getPopularTvShows(): PagingSource<Int, PopularTvShowCache>

    @Query("SELECT * FROM topRatedTvShow")
    fun getTopRatedTvShows(): PagingSource<Int, TopRatedTvShowCache>

    @Query("SELECT * FROM onTvTvShow")
    fun getOnTvTvShows(): PagingSource<Int, OnTvTvShowCache>

    @Query("SELECT * FROM airingTodayTvShow")
    fun getAiringTodayTvShows(): PagingSource<Int, AiringTodayTvShowCache>

    @Query("SELECT * FROM popularTvShow WHERE id = :id")
    fun getPopularTvShow(id: Int): Flow<PopularTvShowCache>

    @Query("SELECT * FROM topRatedTvShow WHERE id = :id")
    fun getTopRatedTvShow(id: Int): Flow<TopRatedTvShowCache>

    @Query("SELECT * FROM onTvTvShow WHERE id = :id")
    fun getOnTvTvShow(id: Int): Flow<OnTvTvShowCache>

    @Query("SELECT * FROM airingTodayTvShow WHERE id = :id")
    fun getAiringTodayTvShow(id: Int): Flow<AiringTodayTvShowCache>

    @Query("DELETE FROM popularTvShow")
    suspend fun deleteAllPopularTvShow()

    @Query("DELETE FROM topRatedTvShow")
    suspend fun deleteAllTopRatedTvShows()

    @Query("DELETE FROM onTvTvShow")
    suspend fun deleteOnTvTvShows()

    @Query("DELETE FROM airingTodayTvShow")
    suspend fun deleteAiringTodayTvShows()
}

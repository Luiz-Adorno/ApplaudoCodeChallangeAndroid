package com.example.applaudo.data.local.paging

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.applaudo.domain.model.AiringTodayTvShowRemoteKeys
import com.example.applaudo.domain.model.OnTvTvShowRemoteKeys
import com.example.applaudo.domain.model.PopularTvShowRemoteKeys
import com.example.applaudo.domain.model.TopRatedTvShowRemoteKeys

@Dao
interface TvShowsRemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPopularTvShowRemote(popularTvShow : List<PopularTvShowRemoteKeys>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTopRatedTvShowRemote(topRatedTvShow : List<TopRatedTvShowRemoteKeys>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOnTvTvShowRemote(onTvTvShow : List<OnTvTvShowRemoteKeys>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAiringTodayTvShowRemote(airingTodayTvShow : List<AiringTodayTvShowRemoteKeys>)

    @Query("SELECT * FROM on_tv_tv_show_remote_keys WHERE id = :id")
    suspend fun getOnTvTvShowRemoteKeys(id: Int): OnTvTvShowRemoteKeys?

    @Query("SELECT * FROM popular_tv_show_remote_keys WHERE id = :id")
    suspend fun getPopularTvShowRemoteKeys(id: Int): PopularTvShowRemoteKeys?

    @Query("SELECT * FROM top_rated_show_remote_keys WHERE id = :id")
    suspend fun getTopRatedTvShowRemoteKeys(id: Int): TopRatedTvShowRemoteKeys?

    @Query("SELECT * FROM airing_today_tv_show_remote_keys WHERE id = :id")
    suspend fun getAiringTodayTvShowRemoteKeys(id: Int): AiringTodayTvShowRemoteKeys?

    @Query("DELETE FROM popular_tv_show_remote_keys")
    suspend fun deletePopularTvShowRemoteKeys()

    @Query("DELETE FROM top_rated_show_remote_keys")
    suspend fun deleteTopRatedTvShowRemoteKeys()

    @Query("DELETE FROM on_tv_tv_show_remote_keys")
    suspend fun deleteOnTvTvShowRemoteKeys()

    @Query("DELETE FROM airing_today_tv_show_remote_keys")
    suspend fun deleteAiringTodayTvShowRemoteKeys()
}
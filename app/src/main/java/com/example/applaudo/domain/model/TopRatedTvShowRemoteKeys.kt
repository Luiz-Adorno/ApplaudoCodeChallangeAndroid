package com.example.applaudo.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "top_rated_show_remote_keys")
data class TopRatedTvShowRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long?,
)

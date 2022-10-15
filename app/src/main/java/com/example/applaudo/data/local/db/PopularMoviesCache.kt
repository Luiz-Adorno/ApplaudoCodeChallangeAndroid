package com.example.applaudo.data.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "popularMovies")
data class PopularMoviesCache(
    @PrimaryKey(autoGenerate = true)
    var pk: Long = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("vote_average")
    val rating: String?,
    @SerializedName("release_date")
    val releaseDate: String?
)
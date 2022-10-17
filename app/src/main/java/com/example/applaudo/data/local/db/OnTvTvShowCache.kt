package com.example.applaudo.data.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "onTvTvShow")
data class OnTvTvShowCache(
    @PrimaryKey(autoGenerate = true)
    var pk: Long = 0,
    @SerializedName("id")
    val id: Int,
    @SerializedName("backdrop_path")
    val backdrop_path: String,
    @SerializedName("first_air_date")
    val first_air_date: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("original_language")
    val original_language: String,
    @SerializedName("original_name")
    val original_name: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val poster_path: String,
    @SerializedName("vote_average")
    val vote_average: Double,
    @SerializedName("vote_count")
    val vote_count: Int
)
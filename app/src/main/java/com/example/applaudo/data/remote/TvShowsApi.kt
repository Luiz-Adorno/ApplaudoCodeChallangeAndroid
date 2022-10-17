package com.example.applaudo.data.remote

import com.example.applaudo.common.Constants
import com.example.applaudo.data.remote.dto.AiringTodayTvShowDto
import com.example.applaudo.data.remote.dto.OnTvTvShowDto
import com.example.applaudo.data.remote.dto.PopularTvShowsDto
import com.example.applaudo.data.remote.dto.TopRatedTvShowDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TvShowsApi {

    @GET("tv/popular")
    suspend fun getPopularTvShows(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("page") page: Int = 1
    ): Response<PopularTvShowsDto>

    @GET("tv/top_rated")
    suspend fun getTopRatedTvShow(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("page") page: Int = 1
    ): Response<TopRatedTvShowDto>

    @GET("tv/on_the_air")
    suspend fun getOnTvTvShows(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("page") page: Int = 1
    ): Response<OnTvTvShowDto>

    @GET("tv/airing_today")
    suspend fun getAiringTodayMovies(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("page") page: Int = 1
    ): Response<AiringTodayTvShowDto>

}
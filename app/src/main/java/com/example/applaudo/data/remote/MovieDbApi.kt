package com.example.applaudo.data.remote

import com.example.applaudo.common.Constants
import com.example.applaudo.data.remote.dto.PopularMoviesDto
import com.example.applaudo.data.remote.dto.TopRatedMoviesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDbApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("page") page: Int = 1
    ): Response<PopularMoviesDto>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("page") page: Int = 1
    ): Response<TopRatedMoviesDto>

}
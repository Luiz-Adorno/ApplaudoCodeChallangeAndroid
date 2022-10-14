package com.example.applaudo.data.remote

import com.example.applaudo.common.Constants
import com.example.applaudo.data.remote.dto.PopularMoviesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDbApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): PopularMoviesDto

}
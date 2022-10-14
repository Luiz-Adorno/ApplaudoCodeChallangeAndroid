package com.example.applaudo.di

import android.content.Context
import com.example.applaudo.common.ConnectivityObserver
import com.example.applaudo.common.Constants
import com.example.applaudo.common.NetworkConnectivityObserver
import com.example.applaudo.data.remote.MovieDbApi
import com.example.applaudo.data.repository.MoviesRepositoryImpl
import com.example.applaudo.domain.MoviesRepository
import com.example.applaudo.domain.use_cases.GetPopularMoviesUseCase
import com.example.applaudo.domain.use_cases.MoviesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .callTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieDbApi(okHttpClient: OkHttpClient): MovieDbApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MovieDbApi::class.java)
    }

    @Provides
    @Singleton
    fun provideConnectivityObserver(context: Context): ConnectivityObserver {
        return NetworkConnectivityObserver(context)
    }


    @Provides
    @Singleton
    fun provideMoviesRepository(api: MovieDbApi): MoviesRepository {
        return MoviesRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideMeUseCases(repository: MoviesRepository): MoviesUseCases {
        return MoviesUseCases(
            getPopularMoviesUseCase = GetPopularMoviesUseCase(repository)
        )
    }
}
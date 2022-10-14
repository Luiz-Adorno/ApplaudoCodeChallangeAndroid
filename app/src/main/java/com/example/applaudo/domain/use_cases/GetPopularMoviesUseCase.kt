package com.example.applaudo.domain.use_cases

import com.example.applaudo.common.Resource
import com.example.applaudo.data.remote.dto.MovieResult
import com.example.applaudo.domain.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MoviesRepository
) {
    operator fun invoke(): Flow<Resource<List<MovieResult>>> = flow {
        try {
            emit(Resource.Loading<List<MovieResult>>())
          //  val popularMovies = repository.getPopularMovies().results
          //  emit(Resource.Success<List<Result>>(popularMovies))
        } catch(e: HttpException) {
            emit(Resource.Error<List<MovieResult>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<List<MovieResult>>("Couldn't reach server. Check your internet connection."))
        }
    }
}
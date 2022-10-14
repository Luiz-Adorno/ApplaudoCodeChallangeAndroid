package com.example.applaudo.data.local.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.applaudo.data.remote.dto.MovieResult
import com.example.applaudo.domain.MoviesRepository

class UsersDataSource(
    private val movies: MoviesRepository
) : PagingSource<Int, MovieResult>() {

    override fun getRefreshKey(state: PagingState<Int, MovieResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResult> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = movies.getPopularMovies(nextPageNumber, 10)
            LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = if (response.results.isNotEmpty()) response.page + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
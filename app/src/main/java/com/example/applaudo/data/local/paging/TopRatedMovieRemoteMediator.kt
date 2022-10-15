package com.example.applaudo.data.local.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.applaudo.data.local.db.MovieDB
import com.example.applaudo.data.local.db.TopRatedMoviesCache
import com.example.applaudo.data.remote.MovieDbApi
import com.example.applaudo.domain.model.MovieRemoteKeys

@OptIn(ExperimentalPagingApi::class)
class TopRatedMovieRemoteMediator(private val movieApi: MovieDbApi, private val movieDB: MovieDB) :
    RemoteMediator<Int, TopRatedMoviesCache>() {

    private val movieDao = movieDB.movieDao()
    private val movieRemoteKeysDao = movieDB.movieRemoteKeysDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, TopRatedMoviesCache>): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }
            val response = movieApi.getTopRatedMovies(page = page)

            var endOfPaginationReached = false
            if (response.isSuccessful) {
                val responseData = response.body()
                endOfPaginationReached = responseData == null
                responseData?.let {
                    movieDB.withTransaction {
                        if (loadType == LoadType.REFRESH) {
                            movieDao.deleteAllTopRatedMovies()
                            movieRemoteKeysDao.deleteAllMovieRemoteKeys()
                        }
                        var prevPage: Int?
                        var nextPage: Int

                        responseData.page.let { pageNumber ->
                            nextPage = pageNumber + 1
                            prevPage = if (pageNumber <= 1) null else pageNumber - 1
                        }

                        val keys = responseData.results.map { movie ->
                            MovieRemoteKeys(
                                id = movie.id,
                                prevPage = prevPage,
                                nextPage = nextPage,
                                lastUpdated = System.currentTimeMillis()
                            )
                        }
                        movieRemoteKeysDao.addPopularMoviesRemote(keys)
                        movieDao.addTopRatedMovies(responseData.results)
                    }
                }

            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, TopRatedMoviesCache>,
    ): MovieRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                movieRemoteKeysDao.getMovieRemoteKeys(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, TopRatedMoviesCache>,
    ): MovieRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { movie ->
                movieRemoteKeysDao.getMovieRemoteKeys(movie.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, TopRatedMoviesCache>,
    ): MovieRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { movie ->
                movieRemoteKeysDao.getMovieRemoteKeys(movie.id)
            }
    }
}
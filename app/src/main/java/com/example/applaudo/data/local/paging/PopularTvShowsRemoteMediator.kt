package com.example.applaudo.data.local.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.applaudo.data.local.db.TvShowDB
import com.example.applaudo.data.local.db.PopularTvShowCache
import com.example.applaudo.data.remote.TvShowsApi
import com.example.applaudo.domain.model.PopularTvShowRemoteKeys

@OptIn(ExperimentalPagingApi::class)
class PopularTvShowsRemoteMediator(private val tvShowsApi: TvShowsApi, private val tvShowDB: TvShowDB) :
    RemoteMediator<Int, PopularTvShowCache>() {

    private val tvShowsDao = tvShowDB.tvShowsDao()
    private val tvShowRemoteKeysDao = tvShowDB.tvShowsRemoteKeysDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, PopularTvShowCache>): MediatorResult {
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

            val response = tvShowsApi.getPopularTvShows(page = page)

            var endOfPaginationReached = false
            if (response.isSuccessful) {
                val responseData = response.body()
                endOfPaginationReached = responseData == null
                responseData?.let {
                    tvShowDB.withTransaction {
                        if (loadType == LoadType.REFRESH) {
                            tvShowsDao.deleteAllPopularTvShow()
                            tvShowRemoteKeysDao.deletePopularTvShowRemoteKeys()
                        }
                        var prevPage: Int?
                        var nextPage: Int

                        responseData.page.let { pageNumber ->
                            nextPage = pageNumber + 1
                            prevPage = if (pageNumber <= 1) null else pageNumber - 1
                        }

                        val keys = responseData.results.map { movie ->
                            PopularTvShowRemoteKeys(
                                id = movie.id,
                                prevPage = prevPage,
                                nextPage = nextPage,
                                lastUpdated = System.currentTimeMillis()
                            )
                        }
                        tvShowRemoteKeysDao.addPopularTvShowRemote(keys)
                        tvShowsDao.addPopularTvShows(responseData.results)
                    }
                }

            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, PopularTvShowCache>,
    ): PopularTvShowRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                tvShowRemoteKeysDao.getPopularTvShowRemoteKeys(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, PopularTvShowCache>,
    ): PopularTvShowRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { movie ->
                tvShowRemoteKeysDao.getPopularTvShowRemoteKeys(movie.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, PopularTvShowCache>,
    ): PopularTvShowRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { movie ->
                tvShowRemoteKeysDao.getPopularTvShowRemoteKeys(movie.id)
            }
    }
}
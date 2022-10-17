package com.example.applaudo.data.local.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.applaudo.data.local.db.OnTvTvShowCache
import com.example.applaudo.data.local.db.TvShowDB
import com.example.applaudo.data.remote.TvShowsApi
import com.example.applaudo.domain.model.OnTvTvShowRemoteKeys

@OptIn(ExperimentalPagingApi::class)
class OnTvTvShowsRemoteMediator(private val tvShowApi: TvShowsApi, private val tvShowDB: TvShowDB) :
    RemoteMediator<Int, OnTvTvShowCache>() {

    private val tvShowsDao = tvShowDB.tvShowsDao()
    private val tvShowRemoteKeysDao = tvShowDB.tvShowsRemoteKeysDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, OnTvTvShowCache>): MediatorResult {
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

            val response = tvShowApi.getOnTvTvShows(page = page)

            var endOfPaginationReached = false
            if (response.isSuccessful) {
                val responseData = response.body()
                endOfPaginationReached = responseData == null
                responseData?.let {
                    tvShowDB.withTransaction {
                        if (loadType == LoadType.REFRESH) {
                            tvShowsDao.deleteOnTvTvShows()
                            tvShowRemoteKeysDao.deleteOnTvTvShowRemoteKeys()
                        }
                        var prevPage: Int?
                        var nextPage: Int

                        responseData.page.let { pageNumber ->
                            nextPage = pageNumber + 1
                            prevPage = if (pageNumber <= 1) null else pageNumber - 1
                        }

                        val keys = responseData.results.map { movie ->
                            OnTvTvShowRemoteKeys(
                                id = movie.id,
                                prevPage = prevPage,
                                nextPage = nextPage,
                                lastUpdated = System.currentTimeMillis()
                            )
                        }
                        tvShowRemoteKeysDao.addOnTvTvShowRemote(keys)
                        tvShowsDao.addOnTvRatedTvShows(responseData.results)
                    }
                }

            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, OnTvTvShowCache>,
    ): OnTvTvShowRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                tvShowRemoteKeysDao.getOnTvTvShowRemoteKeys(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, OnTvTvShowCache>,
    ): OnTvTvShowRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { movie ->
                tvShowRemoteKeysDao.getOnTvTvShowRemoteKeys(movie.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, OnTvTvShowCache>,
    ): OnTvTvShowRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { movie ->
                tvShowRemoteKeysDao.getOnTvTvShowRemoteKeys(movie.id)
            }
    }
}
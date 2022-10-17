package com.example.applaudo.data.local.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.applaudo.data.local.db.TopRatedTvShowCache
import com.example.applaudo.data.local.db.TvShowDB
import com.example.applaudo.data.remote.TvShowsApi
import com.example.applaudo.domain.model.TopRatedTvShowRemoteKeys

@OptIn(ExperimentalPagingApi::class)
class TopRatedTvShowsRemoteMediator(private val tvShowApi: TvShowsApi, private val tvShowDB: TvShowDB) :
    RemoteMediator<Int, TopRatedTvShowCache>() {

    private val tvShowsDao = tvShowDB.tvShowsDao()
    private val tvShowRemoteKeysDao = tvShowDB.tvShowsRemoteKeysDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, TopRatedTvShowCache>): MediatorResult {
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

            val response = tvShowApi.getTopRatedTvShow(page = page)

            var endOfPaginationReached = false
            if (response.isSuccessful) {
                val responseData = response.body()
                endOfPaginationReached = responseData == null
                responseData?.let {
                    tvShowDB.withTransaction {
                        if (loadType == LoadType.REFRESH) {
                            tvShowsDao.deleteAllTopRatedTvShows()
                            tvShowRemoteKeysDao.deleteTopRatedTvShowRemoteKeys()
                        }
                        var prevPage: Int?
                        var nextPage: Int

                        responseData.page.let { pageNumber ->
                            nextPage = pageNumber + 1
                            prevPage = if (pageNumber <= 1) null else pageNumber - 1
                        }

                        val keys = responseData.results.map { movie ->
                            TopRatedTvShowRemoteKeys(
                                id = movie.id,
                                prevPage = prevPage,
                                nextPage = nextPage,
                                lastUpdated = System.currentTimeMillis()
                            )
                        }
                        tvShowRemoteKeysDao.addTopRatedTvShowRemote(keys)
                        tvShowsDao.addTopRatedTvShows(responseData.results)
                    }
                }

            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, TopRatedTvShowCache>,
    ): TopRatedTvShowRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                tvShowRemoteKeysDao.getTopRatedTvShowRemoteKeys(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, TopRatedTvShowCache>,
    ): TopRatedTvShowRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { movie ->
                tvShowRemoteKeysDao.getTopRatedTvShowRemoteKeys(movie.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, TopRatedTvShowCache>,
    ): TopRatedTvShowRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { movie ->
                tvShowRemoteKeysDao.getTopRatedTvShowRemoteKeys(movie.id)
            }
    }
}
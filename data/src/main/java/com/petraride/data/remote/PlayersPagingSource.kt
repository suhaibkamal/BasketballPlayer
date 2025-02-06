package com.petraride.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.petraride.domain.model.Player
import com.petraride.domain.model.PlayerResponse

class PlayersPagingSource(val playersApis: PlayersApis): PagingSource<Int, Player>() {
    override fun getRefreshKey(state: PagingState<Int, Player>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Player> {
        return try {
            val nextPage = params.key?:0
            val response = playersApis.getPlayers(nextPage)
            LoadResult.Page(
                data = response.players,
                prevKey = if (nextPage == 0) null else nextPage - response.meta.perPage,
                nextKey = if (response.players.isEmpty()) null else nextPage + response.meta.perPage
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
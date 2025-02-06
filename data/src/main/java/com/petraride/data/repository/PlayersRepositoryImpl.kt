package com.petraride.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.petraride.data.remote.PlayersApis
import com.petraride.data.remote.PlayersPagingSource
import com.petraride.domain.model.Player
import com.petraride.domain.model.PlayerResponse
import com.petraride.domain.repository.PlayersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlayersRepositoryImpl(private val apiService: PlayersApis): PlayersRepository {
    override  fun getPlayers(): Flow<PagingData<Player>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PlayersPagingSource(apiService)}
                ).flow
    }


    override suspend fun getPlayerById(id: Int) = flow {

        emit(apiService.getPlayerByID(id).player)
    }

}
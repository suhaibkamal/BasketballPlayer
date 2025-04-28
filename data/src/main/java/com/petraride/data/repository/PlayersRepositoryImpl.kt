package com.petraride.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.petraride.data.local.dao.PlayerDAO
import com.petraride.data.local.toDomain
import com.petraride.data.local.toEntity
import com.petraride.data.remote.PlayersApis
import com.petraride.data.remote.PlayersPagingSource
import com.petraride.domain.model.Player
import com.petraride.domain.model.PlayerLocal
import com.petraride.domain.model.PlayerResponse
import com.petraride.domain.repository.PlayersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlayersRepositoryImpl(private val apiService: PlayersApis, private val playerDAO: PlayerDAO): PlayersRepository {
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

    override suspend fun addPlayerToFavorites(player: PlayerLocal) {
        playerDAO.insertPlayer(player.toEntity())
    }

    override suspend fun removePlayerFromFavorites(player: PlayerLocal) {
        playerDAO.deletePlayer(player.toEntity())
    }

    override suspend fun getFavoritePlayers(): Flow<List<PlayerLocal>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPlayerByIdFromLocal(id: Int): PlayerLocal? {
        return playerDAO.getPlayerById(id)?.toDomain()
    }

}
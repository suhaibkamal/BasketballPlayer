package com.petraride.domain.repository

import androidx.paging.PagingData
import com.petraride.domain.model.Player
import com.petraride.domain.model.PlayerLocal
import kotlinx.coroutines.flow.Flow

interface PlayersRepository {
    fun getPlayers(): Flow<PagingData<Player>>
    suspend fun getPlayerById(id: Int): Flow<Player>
    suspend fun addPlayerToFavorites(player: PlayerLocal)
    suspend fun removePlayerFromFavorites(player: PlayerLocal)
    suspend fun getFavoritePlayers(): Flow<List<PlayerLocal>>
    suspend fun getPlayerByIdFromLocal(id: Int): PlayerLocal?

}
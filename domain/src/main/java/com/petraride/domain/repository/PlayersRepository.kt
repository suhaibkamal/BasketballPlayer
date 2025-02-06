package com.petraride.domain.repository

import androidx.paging.PagingData
import com.petraride.domain.model.Player
import kotlinx.coroutines.flow.Flow

interface PlayersRepository {
    fun getPlayers(): Flow<PagingData<Player>>
    suspend fun getPlayerById(id: Int): Flow<Player>
}
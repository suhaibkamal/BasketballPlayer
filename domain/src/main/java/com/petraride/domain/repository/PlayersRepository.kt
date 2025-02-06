package com.petraride.domain.repository

import com.petraride.domain.model.Player
import com.petraride.domain.model.PlayerResponse

interface PlayersRepository {
    suspend fun getPlayers(cursor:Int): PlayerResponse
    suspend fun getPlayerById(id: Int): Player
}
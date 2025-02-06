package com.petraride.data.repository

import com.petraride.data.remote.PlayersApis
import com.petraride.domain.model.Player
import com.petraride.domain.model.PlayerResponse
import com.petraride.domain.repository.PlayersRepository

class PlayersRepositoryImpl(private val apiService: PlayersApis): PlayersRepository {
    override suspend fun getPlayers(cursor: Int): PlayerResponse {
        return apiService.getPlayers(cursor)
    }


    override suspend fun getPlayerById(id: Int): Player {
        return apiService.getPlayerByID(id)
    }

}
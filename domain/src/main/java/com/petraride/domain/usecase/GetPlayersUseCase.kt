package com.petraride.domain.usecase

import com.petraride.domain.model.Player
import com.petraride.domain.model.PlayerResponse
import com.petraride.domain.repository.PlayersRepository

class GetPlayersUseCase(private val repository: PlayersRepository) {
    suspend operator fun invoke(cursor:Int?=0): PlayerResponse {
        return repository.getPlayers(cursor ?:0)
    }
}
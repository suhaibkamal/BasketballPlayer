package com.petraride.domain.usecase

import com.petraride.domain.model.Player
import com.petraride.domain.repository.PlayersRepository

class GetPlayerByIdUseCase(private val repository: PlayersRepository) {
    suspend operator fun invoke(id: Int): Player {
        return repository.getPlayerById(id)
    }
}
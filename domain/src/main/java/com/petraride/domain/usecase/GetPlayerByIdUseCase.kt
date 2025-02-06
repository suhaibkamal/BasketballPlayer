package com.petraride.domain.usecase

import com.petraride.domain.model.Player
import com.petraride.domain.repository.PlayersRepository
import kotlinx.coroutines.flow.Flow

class GetPlayerByIdUseCase(private val repository: PlayersRepository) {
    suspend operator fun invoke(id: Int): Flow<Player>{
        return repository.getPlayerById(id)
    }
}
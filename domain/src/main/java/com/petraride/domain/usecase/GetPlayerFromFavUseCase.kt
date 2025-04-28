package com.petraride.domain.usecase

import com.petraride.domain.model.PlayerLocal
import com.petraride.domain.repository.PlayersRepository

class GetPlayerFromFavUseCase(private val repository: PlayersRepository) {
    suspend operator fun invoke(id: Int): PlayerLocal? {
       return repository.getPlayerByIdFromLocal(id)
    }
}
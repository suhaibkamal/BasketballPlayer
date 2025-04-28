package com.petraride.domain.usecase

import com.petraride.domain.model.PlayerLocal
import com.petraride.domain.repository.PlayersRepository

class RemovePlayerToFavUseCase(private val repository: PlayersRepository) {
    suspend operator fun invoke(playerLocal: PlayerLocal) {
        repository.removePlayerFromFavorites(playerLocal)

    }
}
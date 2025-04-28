package com.petraride.domain.usecase

import com.petraride.domain.model.PlayerLocal
import com.petraride.domain.repository.PlayersRepository

class AddPlayerToFavUseCase(private val repository: PlayersRepository) {
    suspend operator fun invoke(player: PlayerLocal) {
        repository.addPlayerToFavorites(player)
    }

}
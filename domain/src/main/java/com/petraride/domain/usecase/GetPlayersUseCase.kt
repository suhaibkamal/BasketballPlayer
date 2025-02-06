package com.petraride.domain.usecase

import androidx.paging.PagingData
import com.petraride.domain.model.Player
import com.petraride.domain.model.PlayerResponse
import com.petraride.domain.repository.PlayersRepository
import kotlinx.coroutines.flow.Flow

class GetPlayersUseCase(private val repository: PlayersRepository) {
     operator fun invoke(): Flow<PagingData<Player>> {
        return repository.getPlayers()
    }
}
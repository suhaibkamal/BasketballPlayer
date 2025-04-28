package com.petraride.basketplayers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petraride.domain.model.Player
import com.petraride.domain.usecase.PlayersUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import androidx.paging.cachedIn
import com.petraride.domain.model.PlayerLocal


class PlayersViewModel(val playersUseCases: PlayersUseCases): ViewModel() {
     val pagedPlayers = playersUseCases.getPlayersUseCase().cachedIn(viewModelScope)

    private val _player = MutableStateFlow<Player?>(null)
    val player: StateFlow<Player?> = _player.asStateFlow()

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite.asStateFlow()

    fun loadPlayer(id: Int) {
        viewModelScope.launch {
             playersUseCases.getPlayerByIdUseCase(id).collect{
                _player.value=it
                 getPlayerFromFavorites(id)
            }
        }
    }

    fun addPlayerToFavorites(player: Player?) {
        viewModelScope.launch {
            if (player == null) return@launch
            _isFavorite.value=true
            playersUseCases.addPlayerToFavUseCase(PlayerLocal(player.id, player.firstName,
                player.lastName, player.team.name, true))
        }
    }


    fun removePlayerFromFavorites(player: Player?) {
        viewModelScope.launch {
            if (player == null) return@launch
            _isFavorite.value=false
            playersUseCases.addPlayerToFavUseCase(PlayerLocal(player.id, player.firstName,
                player.lastName, player.team.name, false))
        }
    }

    fun getPlayerFromFavorites(id: Int) {
        viewModelScope.launch {
            val player = playersUseCases.getPlayerFromFavUseCase(id)
            _isFavorite.value = player?.isFavorite?: false
        }

    }
}
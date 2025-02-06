package com.petraride.basketplayers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petraride.domain.model.Player
import com.petraride.domain.usecase.PlayersUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PlayersViewModel(val playersUseCases: PlayersUseCases): ViewModel() {

    private val _players = MutableStateFlow<List<Player>>(emptyList())
    val players: StateFlow<List<Player>> = _players.asStateFlow()

    fun loadPlayers() {
        viewModelScope.launch {
            _players.value = playersUseCases.getPlayersUseCase().players
        }
    }
}
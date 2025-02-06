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


class PlayersViewModel(val playersUseCases: PlayersUseCases): ViewModel() {
     val pagedPlayers = playersUseCases.getPlayersUseCase().cachedIn(viewModelScope)

    private val _player = MutableStateFlow<Player?>(null)
    val player: StateFlow<Player?> = _player.asStateFlow()

    fun loadPlayer(id: Int) {
        viewModelScope.launch {
             playersUseCases.getPlayerByIdUseCase(id).collect{
                _player.value=it
            }
        }
    }
}
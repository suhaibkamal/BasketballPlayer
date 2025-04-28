package com.petraride.domain.usecase

data class PlayersUseCases(
    var getPlayersUseCase: GetPlayersUseCase,
    var getPlayerByIdUseCase: GetPlayerByIdUseCase,
    var addPlayerToFavUseCase: AddPlayerToFavUseCase,
    var removePlayerToFavUseCase: RemovePlayerToFavUseCase,
    var getPlayerFromFavUseCase: GetPlayerFromFavUseCase
)
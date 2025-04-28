package com.petraride.domain.model

data class PlayerLocal(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val teamName: String,
    val isFavorite: Boolean
)
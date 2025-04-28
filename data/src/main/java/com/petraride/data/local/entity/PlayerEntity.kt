package com.petraride.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "players")
data class PlayerEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val firstName: String,
    val lastName: String,
    val teamName: String,
    val isFavorite: Boolean
)

package com.petraride.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.petraride.data.local.entity.PlayerEntity

@Dao
interface PlayerDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayer(user: PlayerEntity)
    @Delete
    suspend fun deletePlayer(user: PlayerEntity)

    @Query("SELECT * FROM players WHERE id = :id")
    suspend fun getPlayerById(id: Int): PlayerEntity?
    @Query("SELECT * FROM players")
    suspend fun getAllPlayers(): List<PlayerEntity>
}
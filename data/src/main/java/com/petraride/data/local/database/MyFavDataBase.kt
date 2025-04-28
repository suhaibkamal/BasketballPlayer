package com.petraride.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.petraride.data.local.dao.PlayerDAO
import com.petraride.data.local.entity.PlayerEntity

@Database(entities = [PlayerEntity::class],version = 1 , exportSchema = false)
abstract class MyFavDataBase: RoomDatabase()  {
    abstract fun playerDao(): PlayerDAO

}
package com.example.exemplousodatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Player::class], version = 1)
public abstract class GameDatabase : RoomDatabase() {
    abstract fun playerDao() : PlayerDAO
}
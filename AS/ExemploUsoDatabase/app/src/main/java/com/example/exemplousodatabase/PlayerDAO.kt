package com.example.exemplousodatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
public interface PlayerDAO {

    @Query("SELECT * FROM player")
    fun getAll() : List<Player>

    @Query("SELECT * FROM player WHERE uid IN (:playerIds)")
    fun loadAll(playerIds: IntArray) : List<Player>

    @Query("SELECT * FROM player WHERE nick_name LIKE :nickname")
    fun findByNick(nickname : String) : Player

    @Insert
    fun insertAll(vararg player: Player)

    @Delete
    fun deletePlayer(player: Player)
}
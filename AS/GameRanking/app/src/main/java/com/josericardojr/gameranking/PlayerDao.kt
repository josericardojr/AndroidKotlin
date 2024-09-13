package com.josericardojr.gameranking

import android.provider.ContactsContract.CommonDataKinds.Nickname
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {
    //@Query("SELECT * FROM player_table ORDER BY nick_name ASC")
    //fun getPlayers(): List<Player>

    @Query("SELECT * FROM player_table WHERE nick_name LIKE '%' ||:nickname ||'%' ORDER BY nick_name ASC")
    fun getAllPlayers(nickname: String): Flow<List<Player>>

    @Query("SELECT * FROM player_table ORDER BY nick_name ASC")
    fun getAllPlayers(): Flow<List<Player>> // Flow já é uma função de suspensão, logo não precisa de suspend

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(player: Player)

    @Query("DELETE FROM player_table")
    fun deleteAll()

}
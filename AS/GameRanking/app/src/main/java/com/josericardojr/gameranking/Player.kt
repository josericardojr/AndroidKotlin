package com.josericardojr.gameranking

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player_table")
data class Player(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo("nick_name") val nickname: String,
    @ColumnInfo(name = "score") val score: Int
)

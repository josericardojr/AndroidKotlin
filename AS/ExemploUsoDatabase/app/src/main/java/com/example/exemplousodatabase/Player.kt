package com.example.exemplousodatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
public class Player {
    @PrimaryKey(autoGenerate = true)
    public var uid : Int = 0

    @ColumnInfo(name = "nick_name")
    public var nickname : String = ""

    @ColumnInfo(name = "score")
    public var score : Int = 0
}
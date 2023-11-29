package com.example.exemplousodatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var gameDatabase: GameDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameDatabase = Room.databaseBuilder(this,
            GameDatabase::class.java, "game_database").build()

        var btnInsert = findViewById<Button>(R.id.btnInsert)
        btnInsert.setOnClickListener {

            GlobalScope.launch {
                val edtNick = findViewById<EditText>(R.id.edtNick)
                val edtScore = findViewById<EditText>(R.id.edtScore)

                val playerDao = gameDatabase.playerDao()

                var player = Player()
                player.nickname = edtNick.text.toString()
                player.score = edtScore.text.toString().toInt()

                playerDao.insertAll(player)
            }
        }

        val btnListAll = findViewById<Button>(R.id.btnListAll)
        btnListAll.setOnClickListener {

            GlobalScope.launch(Dispatchers.IO){
                val playerDao = gameDatabase.playerDao()

                for (p in playerDao.getAll()){
                    Log.i("Database", "ID: ${p.uid} Nick: ${p.nickname} Score: ${p.score}")
                }
            }
        }
    }
}
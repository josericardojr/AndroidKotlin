package com.josericardojr.gameranking

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainActivity: AppCompatActivity() {

    private val gameViewModel: GameViewModel by viewModels {
        GameViewModelFactory((application as GameApplication).database)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_layout)

        gameViewModel.playerResult.asLiveData().observe(
            this, Observer { players ->
                findViewById<EditText>(R.id.edtMLText).text.clear()
                players?.forEach {
                    findViewById<EditText>(R.id.edtMLText).text.append(it.toString()+"\n")
                }
            }
        )

        val btnInserir = findViewById<Button>(R.id.btnSalvar)
        btnInserir.setOnClickListener {
            val player = Player(
                nickname = findViewById<EditText>(R.id.edtNick).text.toString(),
                score = findViewById<EditText>(R.id.edtScore).text.toString().toInt()
            )

            gameViewModel.insert(player)
        }

        val btnPesquisar = findViewById<Button>(R.id.btnPesquisar)
        btnPesquisar.setOnClickListener {
            val text = findViewById<EditText>(R.id.edtNickSearch).text.toString()
            gameViewModel.findPlayer(text)
        }
    }
}
package com.josericardo_jr.roomdbexample

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ScoreActivity : AppCompatActivity() {
    val playerScoreList = listOf<PlayerScore>(
        PlayerScore(1, "Testes", 100),
        PlayerScore(2, "Testes2", 300)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score)

        // 1 - Recuperar o RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        // 2 - Criar um gerenciardo de layout para exibir os dados
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Vinculando o nosso adapter
        recyclerView.adapter = PlayerScoreAdapter(playerScoreList)
    }
}
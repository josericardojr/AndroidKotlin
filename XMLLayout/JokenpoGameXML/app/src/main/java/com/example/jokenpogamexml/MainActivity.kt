package com.example.jokenpogamexml

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jokenpogamexml.ui.theme.JokenpoGameXMLTheme

class MainActivity : ComponentActivity() {
    lateinit var btnPapel : ImageButton
    lateinit var btnTesoura : ImageButton
    lateinit var btnPedra : ImageButton
    lateinit var btnReiniciar : Button
    lateinit var txtPlacarJogador : TextView
    lateinit var txtPlacarOponente : TextView
    //0 = Papel; 1 = Pedra; 2 = Tesoura
    var placarJogador : Int = 0
    var placarOponente : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_game)


        // Recupear buttons
        btnPapel = findViewById<ImageButton>(R.id.btnPapel)
        btnPedra = findViewById<ImageButton>(R.id.btnPedra)
        btnTesoura = findViewById<ImageButton>(R.id.btnTesoura)
        btnReiniciar = findViewById<Button>(R.id.btnReset)

        // Recuperar textos de exibicao
        txtPlacarJogador = findViewById<TextView>(R.id.txtJogador)
        txtPlacarOponente = findViewById<TextView>(R.id.txtOponente)

        // Processamento da jogada
        var processamentoJogada : (Int) -> Unit = { opJogador ->

            // Jogada do oponente
            var opcaoOponente = jogadaOponente()

            // Verificar se o jogador ganhou
            if (verificarSeGanhou(opJogador, opcaoOponente)){
                // Jogador Ganhou
                placarJogador += 1
            } else if (verificarSeGanhou(opcaoOponente, opJogador)) {
                placarOponente += 1
            }

            txtPlacarJogador.text = placarJogador.toString()
            txtPlacarOponente.text = placarOponente.toString()

        }

        // Implementar funcionalidades das mecanicas de jogo
        btnPapel.setOnClickListener { processamentoJogada(0) }
        btnPedra.setOnClickListener { processamentoJogada(1) }
        btnTesoura.setOnClickListener { processamentoJogada(2) }

        // Implementar reset do placar
        btnReiniciar.setOnClickListener {
            placarJogador = 0
            placarOponente = 0

            txtPlacarJogador.text = placarJogador.toString()
            txtPlacarOponente.text = placarOponente.toString()

            val imgOponente = findViewById<ImageView>(R.id.imgOponente)
            imgOponente.setImageResource(R.drawable.padrao)
        }

    }


    fun jogadaOponente() : Int {
        val opcaoOponente = (Math.random() * 2.0).toInt()

        val imgOponente = findViewById<ImageView>(R.id.imgOponente)

        when (opcaoOponente){
            0 -> imgOponente.setImageResource(R.drawable.papel)
            1 -> imgOponente.setImageResource(R.drawable.pedra)
            2 -> imgOponente.setImageResource(R.drawable.tesoura)
        }

        return opcaoOponente
    }

    fun verificarSeGanhou(opcao1: Int, opcao2: Int) : Boolean {
        if (opcao1 == 0 && opcao2 == 1) return true
        if (opcao1 == 1 && opcao2 == 2) return true
        if (opcao1 == 2 && opcao2 == 0) return true

        return false
    }
}

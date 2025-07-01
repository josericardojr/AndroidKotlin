package com.example.jokenpo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jokenpo.ui.JokenpoGame
import com.example.jokenpo.ui.theme.JokenpoTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {
            JokenpoTheme {

                @DrawableRes var enemyImage by remember { mutableStateOf(R.drawable.padrao) }
                var placarJogador by remember { mutableStateOf(0) }
                var placarAdversario by remember { mutableStateOf(0) }


                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    JokenpoGame(
                        modifier = Modifier.padding(innerPadding),
                        onClick = { playerOption ->

                            val enemyOption = makeOpponentPlay()

                            enemyImage = when (enemyOption) {
                                PlayOption.Pedra -> R.drawable.pedra
                                PlayOption.Papel -> R.drawable.papel
                                PlayOption.Tesoura -> R.drawable.tesoura
                                else -> R.drawable.padrao
                            }

                            if (checkVictory(playerOption, enemyOption))  placarJogador++
                            else if (checkVictory(enemyOption, playerOption)) placarAdversario++
                        },

                        // Restart
                        onRestart = {
                            enemyImage = R.drawable.padrao
                            placarJogador = 0
                            placarAdversario = 0
                        },

                        enemyImage =  enemyImage,
                        placarJogador = placarJogador,
                        placarAdversario = placarAdversario
                    )
                }
            }
        }


    }



    fun onClickPlayer(playOption: PlayOption) {
        // Verificar e atualizar a jogada do jogador

        makeOpponentPlay()
    }

    fun makeOpponentPlay() : PlayOption{
        val option = PlayOption.entries.toList().shuffled().first()

        return option
    }


    // Metodo chamado para verificar a vitoria do jogador.
    fun checkVictory(
        option1 : PlayOption,
        option2 : PlayOption
    ) : Boolean {

        val result =
            when (option1) {
                PlayOption.Pedra ->
                    option2 == PlayOption.Tesoura
                PlayOption.Tesoura ->
                    option2 == PlayOption.Papel
                PlayOption.Papel ->
                    option2 == PlayOption.Pedra

                else ->
                    false
            }

        return result
    }
}

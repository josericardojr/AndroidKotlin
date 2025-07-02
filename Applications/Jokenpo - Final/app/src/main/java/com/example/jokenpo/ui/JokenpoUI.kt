package com.example.jokenpo.ui

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jokenpo.JokenpoViewModel
import com.example.jokenpo.MainActivity
import com.example.jokenpo.PlayOption
import com.example.jokenpo.R
import com.example.jokenpo.ui.theme.JokenpoTheme


@Composable
fun JokenpoGame(
    modifier: Modifier = Modifier,
    gameViewModel: JokenpoViewModel = viewModel()
) {
    val uiState by gameViewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Jokenpô Game",
                style = MaterialTheme.typography.headlineLarge
            )
        }

        OpcaoJogador(onClick = {gameViewModel.onPlayerMakeOption(it)})

        Spacer(modifier = Modifier.size(32.dp))
        Oponente(uiState.enemyImage)

        Spacer(modifier = Modifier.size(32.dp))
        Placar(
            placarJogador = uiState.placarJogador,
            placarAdversario = uiState.placarAdversario
        )

        Spacer(modifier = Modifier.size(32.dp))
        OutlinedButton(
            onClick = {gameViewModel.resetGame()}
        ) {
            Text("Reiniciar")
        }
    }



}

@Composable
private fun Oponente(
    @DrawableRes image : Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment =  Alignment.CenterHorizontally
    ) {
        Text(
            "Jogada do Oponente",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(16.dp))
        Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(image),
            contentDescription = "Padrao"
        )
    }
}

@Composable
private fun OpcaoJogador(
    modifier: Modifier = Modifier,
    onClick: (PlayOption) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Escolha uma Opção",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.size(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .size(100.dp)
                    .weight(1.0f)
                    .clickable {
                        onClick(PlayOption.Papel)
                               },
                contentScale = ContentScale.Fit,
                painter = painterResource(R.drawable.papel),
                contentDescription = "Papel"
            )

            Image(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .size(100.dp)
                    .weight(1.0f)
                    .clickable {
                        onClick(PlayOption.Pedra)
                    },
                contentScale = ContentScale.Fit,
                painter = painterResource(R.drawable.pedra),
                contentDescription = "Pedra"
            )

            Image(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .size(100.dp)
                    .weight(1.0f)
                    .clickable {
                        onClick(PlayOption.Tesoura)
                    },
                contentScale = ContentScale.Fit,
                painter = painterResource(R.drawable.tesoura),
                contentDescription = "Tesoura"
            )
        }
    }
}

// Placar
@Composable
private fun Placar(
    modifier: Modifier = Modifier,
    placarJogador: Int,
    placarAdversario: Int
) {
    Card {
        Column(
            modifier = modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Placar",
                style = MaterialTheme.typography.titleMedium
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Jogador")
                    Text("$placarJogador")
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Oponente")
                    Text("$placarAdversario")
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JokenpoTheme {
        JokenpoGame(
            modifier = Modifier.fillMaxSize()
        )
    }
}
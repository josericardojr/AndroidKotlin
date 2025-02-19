package com.josericardo_jr.tapthemole.ui

import android.app.Activity
import android.content.res.Resources.Theme
import android.util.Log
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josericardo_jr.tapthemole.R
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MainScreenGame(
    viewModel: MainActivityViewModel = viewModel(),
    modifier: Modifier = Modifier
) {

    val moleState by viewModel.moleState.collectAsState()
    val listOfMoles = moleState.moles

    Log.i("MoleTeste", listOfMoles.size.toString())
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row (
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Tap the Mole!",
                style = MaterialTheme.typography.titleLarge
            )
        }

        Spacer(modifier = Modifier.size(16.dp))

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Timer: ${moleState.timer}")
            Text(text = "Points: ${moleState.score}")
        }
        Spacer(modifier = Modifier.size(16.dp))

        MolesPanel(
            modifier = Modifier.fillMaxSize(),
            moles = listOfMoles,
            onClickMole = { viewModel.onClick() }
        )


    }

    if (moleState.isGameOver) {
        GameOver(
            onRestartGame = { viewModel.resetGame() },
        )
    }
}

@Composable
fun GameOver(
    onRestartGame: () -> Unit,
    modifier: Modifier = Modifier)
{
    val activity = LocalActivity.current

    AlertDialog(
        title = { Text(text = "Game Over")},
        onDismissRequest = { },
        text = { Text(text = "Restart the game?")},
        confirmButton = {
            TextButton(
                onClick = { onRestartGame() }
            ) {
                Text(text = "Jogar Novamente")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {activity?.finish()}
            ) {
                Text(text = "Sair")
            }
        }
    )
}

@Composable
fun MolesPanel(
    modifier: Modifier = Modifier,
    moles: List<MoleState>,
    onClickMole: () -> Unit
){
    Column(modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly) {
        for (k in 0..2) {
            val idx = k * 3

            Row(modifier = Modifier.fillMaxWidth()) {
                for (i in 0..2) {
                    val mole = moles[idx + i]

                    Box(modifier = Modifier.weight(1.0f)) {
                        Image(
                            painter = painterResource(id = R.drawable.mole_hill),
                            contentDescription = "Mole Hill",
                            contentScale = ContentScale.Inside,
                            modifier = Modifier
                                .padding(8.dp)
                        )


                        if (mole.isVisible) {
                            Image(
                                painter = painterResource(id = R.drawable.mole_head),
                                contentDescription = "Mole",
                                contentScale = ContentScale.Inside,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .offset(y = (-80).dp)
                                    .clickable { onClickMole() }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenGamePreview() {
    MainScreenGame(
        modifier = Modifier.fillMaxSize()
    )
}


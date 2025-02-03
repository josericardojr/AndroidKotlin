package com.josericardo_jr.unscrable

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josericardo_jr.unscrable.ui.theme.UnscrableTheme

@Composable
fun GameScreen(
    modifier: Modifier = Modifier
){
    // Palavra atual
    var currentWord by remember { mutableStateOf(allWords.random()) }

    // Palavra embaralhada
    var currentScrambleWord by remember {
        mutableStateOf(String(
            currentWord.toCharArray().apply { shuffle() }
        ))
    }

    // Fim de Jogo?
    var isGameOver by remember { mutableStateOf(false) }

    // Pontuação
    var score by remember { mutableIntStateOf(0) }

    // Total de Palavras jogadas
    var currentWordCount by remember { mutableIntStateOf(1) }

    // Armazena o chute atual do jogador
    var userGuess by remember {
        mutableStateOf("")
    }

    var isGuessWrong by remember { mutableStateOf(false) }

    // Padding definido em um arquivo externo
    val mediumPadding = dimensionResource(id = R.dimen.padding_medium)

    Column(
        modifier = Modifier
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.titleLarge
        )
        GameLayout(
            currentScrambleWord,
            userGuess,
            isGuessWrong,
            currentWordCount,
            onUserGuessChanged = { userGuess = it },
            onKeyboardDone = {
                if (userGuess.equals(currentWord, ignoreCase = true)) {
                    score = score.plus(1)

                    // Próxima palavra
                    currentWordCount = currentWordCount.inc()

                    // Verificar se atingimos o número maximo de palavras
                    if (currentWordCount < 11) {
                        currentWord = allWords.random()
                        currentScrambleWord = String(currentWord.toCharArray().apply { shuffle() })
                        userGuess = ""
                        isGuessWrong = false
                    } else {
                        isGameOver = true
                    }
                } else {
                    isGuessWrong = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(dimensionResource(id = R.dimen.padding_medium))
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(mediumPadding),
            verticalArrangement = Arrangement.spacedBy(mediumPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (userGuess.equals(currentWord, ignoreCase = true)) {
                        score = score.plus(1)

                        // Próxima palavra
                        currentWordCount = currentWordCount.inc()

                        // Verificar se atingimos o número maximo de palavras
                        if (currentWordCount < 11) {
                            currentWord = allWords.random()
                            currentScrambleWord = String(currentWord.toCharArray().apply { shuffle() })
                            userGuess = ""
                            isGuessWrong = false
                        } else {
                            isGameOver = true
                        }
                    } else {
                        isGuessWrong = true
                    }
                }
            ) {
                Text(
                    text = stringResource(R.string.submit),
                    fontSize = 16.sp
                )
            }

            OutlinedButton(
                onClick = {
                    // Próxima palavra
                    currentWordCount = currentWordCount.inc()

                    // Verificar se atingimos o número maximo de palavras
                    if (currentWordCount < 11) {
                        currentWord = allWords.random()
                        currentScrambleWord = String(currentWord.toCharArray().apply { shuffle() })
                        userGuess = ""
                        isGuessWrong = false
                    } else {
                        isGameOver = true
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.skip),
                    fontSize = 16.sp
                )
            }
        }

        if (isGameOver) {
            FinalScoreDialog(
                score = score,
                onPlayAgain = {
                    currentWord = allWords.random()
                    currentScrambleWord = String(currentWord.toCharArray().apply { shuffle() })
                    userGuess = ""
                    isGameOver = false
                    isGuessWrong = false
                    score = 0
                    currentWordCount = 1
                })
        }

        GameStatus(score = score, modifier = Modifier.padding(20.dp))
    }
}

@Composable
fun GameLayout(
    currentScranbleWord: String,
    userGuess: String,
    isGuessWrong: Boolean,
    currentWordCount: Int,
    onUserGuessChanged: (String) -> Unit,
    onKeyboardDone: () -> Unit,
    modifier: Modifier = Modifier
){
    val mediumPadding = dimensionResource(id = R.dimen.padding_medium)
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(mediumPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(mediumPadding)
        ) {
            Text(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.surfaceTint)
                    .padding(horizontal = 4.dp, vertical = 4.dp)
                    .align(Alignment.End),
                text = stringResource(id = R.string.word_count, currentWordCount),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = currentScranbleWord,
                style = MaterialTheme.typography.displayMedium
            )
            Text(
                text = stringResource(id = R.string.instructions),
                style = MaterialTheme.typography.titleMedium
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    disabledContainerColor = MaterialTheme.colorScheme.surface
                ),
                shape = MaterialTheme.shapes.large,
                value = userGuess,
                singleLine = true,
                isError = isGuessWrong,
                label = {
                    if (isGuessWrong) {
                        Text(text = stringResource(id = R.string.wrong_guess))
                    } else {
                        Text(text = stringResource(id = R.string.enter_your_word))
                    }
                },
                onValueChange = onUserGuessChanged,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { onKeyboardDone() }
                )
            )
        }
    }
}

@Composable
fun GameStatus(score: Int, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.score, score),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(8.dp)
        )
    }
}


/**
 * Criar um diálogo exibido o score final do jogador
 */
@Composable
private fun FinalScoreDialog(
    score: Int,
    onPlayAgain: () -> Unit,
    modifier: Modifier = Modifier
){
    val activity = (LocalContext.current as Activity)

    AlertDialog(
        onDismissRequest = {  },
        title = { Text(text = stringResource(id = R.string.congratulations)) },
        text = { Text(text = stringResource(id = R.string.you_scored, score))},
        dismissButton = {
            TextButton(
                onClick = { activity.finish() }
            ) {
               Text(text = stringResource(id = R.string.exit))
            }
        },
        confirmButton = {
            TextButton(
                onClick = { onPlayAgain() }
            ) {
                Text(text = stringResource(id = R.string.play_again))
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun GameScreenPreview(){
    UnscrableTheme {
        GameScreen()
    }
}
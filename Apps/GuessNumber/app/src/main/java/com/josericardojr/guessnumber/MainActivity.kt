package com.josericardojr.guessnumber

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import androidx.lifecycle.lifecycleScope
import com.josericardojr.guessnumber.ui.theme.GuessNumberTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val onSave: (String) -> Unit = {
            val sharedPref = getPreferences(MODE_PRIVATE)

            sharedPref.edit (commit = true) {
                putString("Name", it)
            }
        }
        enableEdgeToEdge()
        setContent {
            GuessNumberTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GuessNumber(
                        onSave = onSave,
                        onRetrieve = {
                            val sharedPref = getPreferences(MODE_PRIVATE)
                            val name = sharedPref.getString("Name", "")
                            Toast.makeText(this, name, Toast.LENGTH_SHORT).show()
                        },
                        modifier =  Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun GuessNumber(
    modifier: Modifier = Modifier,
    onSave: (String) -> Unit,
    onRetrieve: () -> Unit
) {
    var targetNumber by remember { mutableStateOf(Random.nextInt(0, 100)) }
    var minNumber by remember { mutableStateOf("1") }
    var maxNumber by remember { mutableStateOf("100") }
    var showDialog by remember { mutableStateOf(false) }
    var numTrials by remember { mutableStateOf(0) }
    var currentGuess by remember { mutableStateOf("0") }
    var currentMessage by remember { mutableStateOf("") }



    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
                .padding(4.dp)
        ) {
            OutlinedTextField(
                value = minNumber,
                label = {
                    Text(text = "min")
                },
                onValueChange = {
                        if (it.all { it.isDigit() })
                            minNumber = it
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = modifier.weight(1f)
            )

            OutlinedTextField(
                value = maxNumber,
                label = {
                    Text(text = "max")
                },
                onValueChange = {maxNumber = it},
                modifier = modifier.weight(1f)
            )

            Button(
                modifier = modifier.weight(1.5f),
                onClick = {
                    if (minNumber.all { it.isDigit()} && maxNumber.all { it.isDigit() }){
                        val min = minNumber.toInt()
                        val max = maxNumber.toInt()

                        targetNumber = Random.nextInt(min, max)
                        numTrials = 0
                    } else {
                        showDialog = true
                    }
                }

            ) {
                Text(text = "Set")
            }
        }

        Spacer(modifier = Modifier.padding(16.dp))
        OutlinedTextField(
            value = currentGuess,
            label = {
                Text(text = "Enter the value")
            },
            onValueChange = {currentGuess = it}
        )

        Button(
            onClick = {
                numTrials += 1

                val current = currentGuess.toInt()

                currentMessage = if (current == targetNumber)
                    "Congratulations! You reached the answer in $numTrials trials"
                else if (current < targetNumber)
                    "The number is over."
                else
                    "The number is below."
            }
        ) {
            Text(text = "Guess")
        }

        Button(
            onClick = { onSave(currentGuess) }
        ) {
            Text(text = "Save")
        }

        Button(
            onClick = onRetrieve
        ) {
            Text(text = "Retrieve")
        }

        // Exibir mensagem de erro?
        if (showDialog) {
            AlertDialog(
                text = {
                    Text(text = "Min or Max are not number!")
                },
                confirmButton = {
                    Button(
                        onClick = { showDialog = false }
                    ) {
                        Text(text = "OK")
                    }
                },
                onDismissRequest = { showDialog = false }
            )
        }

        if (numTrials > 0){
            Text(text = currentMessage)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GuessNumberPreview() {
    GuessNumberTheme {
        GuessNumber(
            onSave = { it ->
                Log.i("MainActivity", it)
            },
            onRetrieve = {}
        )
    }
}
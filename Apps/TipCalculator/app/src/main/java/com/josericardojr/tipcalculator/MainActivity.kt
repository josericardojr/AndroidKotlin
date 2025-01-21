package com.josericardojr.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josericardojr.tipcalculator.ui.theme.TipCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipCalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TipScreen(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun TipScreen(modifier: Modifier = Modifier) {

    val amountInput = remember { mutableStateOf("") }
    val tipInput = remember { mutableStateOf("") }
    val roundUp = remember { mutableStateOf(false) }

    val amount = amountInput.value.toDoubleOrNull() ?: 0.0
    val tipPercent = tipInput.value.toDoubleOrNull() ?: 0.0
    val tip = calculateTip(amount, tipPercent, roundUp.value)

    Column(
        modifier = modifier
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Calculate Tip",
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 16.dp)
        )


        InputValue(
            text = "Bill Amount",
            value = amountInput.value, // Observar que esta propriedade recebe a variável que
                                        // controla as modificações e não um valor constante.
            onValueChange = { amountInput.value = it },
            icon = R.drawable.money,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        InputValue(
            text = "Tip (%)",
            value = tipInput.value,
            onValueChange = { tipInput.value = it },
            icon = R.drawable.percent,
            keyboardOptions =  KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Round up tip?"
            )

            Switch(
                checked = roundUp.value,
                onCheckedChange = {roundUp.value = !roundUp.value}
            )
        }

        Text(
            text = "Tip amount: $tip",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold
        )
    }
}

/***
 * Função de composição que adiciona um botão personalizado
 */
@Composable
private fun InputValue(
    text : String,
    value : String,
    onValueChange: (String) -> Unit,
    @DrawableRes icon : Int,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        label = {
            Text(text = text)
        },
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null
            )
        },
        keyboardOptions = keyboardOptions,
        modifier = modifier
    )
}

/***
 * Função que calcula o valor da gorjeta
 */
fun calculateTip(amount: Double, tipPercent: Double, round: Boolean): Any {
    val tip = tipPercent / 100 * amount

    if (round) {
        return kotlin.math.ceil(tip)
    }
    return tip
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    TipCalculatorTheme {
        TipScreen(
            modifier = Modifier.fillMaxSize()
        )
    }
}
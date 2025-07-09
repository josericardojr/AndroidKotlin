package com.josericardo_jr.basiccorrotine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.josericardo_jr.basiccorrotine.ui.theme.BasicCorrotineTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    private val basicCorroutineViewModel : BasicCorroutineViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicCorrotineTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CorroutineExample(
                        modifier = Modifier.padding(innerPadding),
                        basicCorroutineViewModel = basicCorroutineViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun CorroutineExample(
    basicCorroutineViewModel: BasicCorroutineViewModel = viewModel(),
    modifier: Modifier = Modifier
){
    val texto: String by basicCorroutineViewModel.texto.collectAsStateWithLifecycle() // Somente coleta os estados quando o componente estiver visível
    val carregando by basicCorroutineViewModel.carregando.collectAsStateWithLifecycle() // Somente coleta os estados quando o componente estiver visível

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(texto)
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            enabled = !carregando,
            onClick = { basicCorroutineViewModel.carregarDados() }
        ) {
            Text("Carregar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BasicCorrotineTheme {
        CorroutineExample()
    }
}
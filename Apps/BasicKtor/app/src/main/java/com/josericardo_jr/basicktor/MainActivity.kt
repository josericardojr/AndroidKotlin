package com.josericardo_jr.basicktor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josericardo_jr.basicktor.ui.theme.BasicKtorTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicKtorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PostScreen(
                        viewModel = viewModels<BasicKtorViewModel>().value,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PostScreen(
    viewModel: BasicKtorViewModel,
    modifier: Modifier = Modifier
) {
    val text by viewModel.text.collectAsState()
    val carregando by viewModel.carregando.collectAsState()

    Column(
        modifier = modifier
            .padding(24.dp)
    ) {
        TextField(
            value = text,
            onValueChange = {}
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            enabled = !carregando,
            onClick = {viewModel.carregarPost()}
        ) {
            Text("Carregar Post")
        }
    }
}
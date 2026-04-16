package com.example.gameloop

import android.os.Bundle
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
import com.example.gameloop.ui.theme.GameLoopTheme

class MainActivity : ComponentActivity() {
    lateinit var drawView: DrawView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        drawView = DrawView(this)
        setContentView(drawView)
    }

    override fun onStart() {
        super.onStart()
        drawView.startGame()
    }

    override fun onStop() {
        super.onStop()
        drawView.stopGame()
    }
}

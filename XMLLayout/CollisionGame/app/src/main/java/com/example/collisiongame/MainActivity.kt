package com.example.collisiongame

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
import com.example.collisiongame.ui.theme.CollisionGameTheme

class MainActivity : ComponentActivity() {

    lateinit var gameView: GameView
    lateinit var gameThread: GameThread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gameView = GameView(this)
        setContentView(gameView)
        gameThread = GameThread(gameView)
    }

    override fun onStart() {
        super.onStart()
        gameThread.startGame()
    }

    override fun onStop() {
        super.onStop()
        gameThread.stopGame()
    }


}

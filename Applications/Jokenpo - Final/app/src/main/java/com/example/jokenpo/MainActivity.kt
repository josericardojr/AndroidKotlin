package com.example.jokenpo

import android.media.MediaPlayer
import android.os.Bundle
import android.provider.MediaStore.Audio.Media
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

    private lateinit var backgroundMusic : MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {
            JokenpoTheme {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    JokenpoGame(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        // Carregar música de fundo do jogo
        backgroundMusic = MediaPlayer.create(this, R.raw.background)
    }

    override fun onStart() {
        super.onStart()
        backgroundMusic.start()
        backgroundMusic.isLooping = true
    }

    override fun onStop() {
        super.onStop()
        backgroundMusic.stop()
    }
}



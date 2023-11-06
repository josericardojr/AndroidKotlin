package com.example.notcollidegame

import android.app.Activity
import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    lateinit var drawView: DrawView

    lateinit var game_music : MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        drawView = DrawView(this)

        setContentView(drawView)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        game_music = MediaPlayer.create(this, R.raw.game_music)
        game_music.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        game_music.stop()
    }
}
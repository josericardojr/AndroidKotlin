package com.josericardo_jr.timertest

import android.os.Bundle
import android.widget.Button
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
import com.josericardo_jr.timertest.ui.theme.TimerTestTheme

class MainActivity : ComponentActivity() {
    lateinit var mySurface : MySurface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_layout)
        mySurface = findViewById<MySurface>(R.id.mySurface)
        findViewById<Button>(R.id.btnRestart).setOnClickListener {
            mySurface.restart()
        }

    }
}
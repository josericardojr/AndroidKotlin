package com.josericardo_jr.canvasapp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
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
import com.josericardo_jr.canvasapp.ui.theme.CanvasAppTheme

class MainActivity : ComponentActivity() {
    lateinit var spriteView: SpriteView
    lateinit var btnLeft: ImageButton
    lateinit var btnRight: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        spriteView = findViewById<SpriteView>(R.id.sprDracula)
        btnLeft = findViewById<ImageButton>(R.id.btnLeft)
        btnRight = findViewById<ImageButton>(R.id.btnRight)

        btnLeft.setOnClickListener {
            spriteView.positionX -= 50
            spriteView.invalidate()
        }

        btnRight.setOnClickListener {
            spriteView.positionX += 50
            spriteView.invalidate()
        }

    }
}
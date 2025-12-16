package com.josericardo_jr.vibrationeffect

import android.os.Bundle
import android.os.CountDownTimer
import android.os.VibrationEffect
import android.os.Vibrator
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
import androidx.core.view.WindowCompat
import com.josericardo_jr.vibrationeffect.ui.theme.VibrationEffectTheme

class MainActivity : ComponentActivity() {

    lateinit var vibrator : Vibrator
    var inLoop = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vibrator = getSystemService(Vibrator::class.java)


        setContentView(R.layout.main_layout)

        findViewById<Button>(R.id.btnClickVib).setOnClickListener {
            vibrator.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK))
        }

        findViewById<Button>(R.id.btnCustomVibration).setOnClickListener {
            val timings: LongArray = longArrayOf(
                50, 50, 50, 50, 50, 100, 350, 25, 25, 25, 25, 200
            )
            val amplitudes: IntArray = intArrayOf(
                33, 51, 75, 113, 170, 255, 0, 38, 62, 100, 160, 255
            )
            val repeatIndex = -1 // Don't repeat.

            vibrator.vibrate(
                VibrationEffect.createWaveform(
                    timings, amplitudes, repeatIndex
                )
            )
        }


        findViewById<Button>(R.id.btnRepeatedCustomVib).setOnClickListener { button ->
            val timings: LongArray = longArrayOf(50, 50, 100, 50, 50)
            val amplitudes: IntArray = intArrayOf(64, 128, 255, 128, 64)
            val repeat = 1 // Repeat from the second entry, index = 1.
            val repeatingEffect = VibrationEffect.createWaveform(
                timings, amplitudes, repeat
            )

            inLoop = !inLoop

            if (inLoop)
                vibrator.vibrate(repeatingEffect)
            else
                vibrator.cancel()


            (button as Button).text = if (inLoop)
                "Stop Loop Vibration"
            else
                "Start Loop Vibration"
        }
    }

    override fun onStop() {
        super.onStop()
        vibrator.cancel()
    }
}
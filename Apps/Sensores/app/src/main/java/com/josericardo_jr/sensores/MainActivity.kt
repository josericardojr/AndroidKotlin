package com.josericardo_jr.sensores

import DrawView
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
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
import androidx.lifecycle.lifecycleScope
import com.josericardo_jr.sensores.ui.theme.SensoresTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var sensorManager: SensorManager
    private var accelSensor: Sensor? = null
    private lateinit var drawView: DrawView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        drawView = DrawView(this)
        setContentView(drawView)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        // Recuperar todos os sensores
        val sensors = sensorManager.getSensorList(Sensor.TYPE_ALL)

        // Imprimir todos os sensores
        sensors.forEach {
            Log.i("Sensores", it.name)
        }

        // Acelerometo
        accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        lifecycleScope.launch (Dispatchers.Default) {
            while (true) {
                drawView.render()
                Thread.sleep(100)
            }
        }
    }

    override fun onResume() {
        super.onResume()

        sensorManager.registerListener(drawView, accelSensor, SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onStop() {
        super.onStop()

        sensorManager.unregisterListener(drawView, accelSensor)
    }
}
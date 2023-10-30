package com.example.sensorball

import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    lateinit var drawView: DrawView
    lateinit var sensorManager : SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        drawView = DrawView(this)
        setContentView(drawView)

        // Recuperar o gerenciador de sensores
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        // Imprimir o nome de todos os sensores disponiveis
        for (sensor in sensorManager.getSensorList(Sensor.TYPE_ALL)){
            Log.i("SENSORES", sensor.name)
        }
    }

    override fun onStart() {
        super.onStart()

        val sensorAccel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(drawView, sensorAccel, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onStop() {
        super.onStop()

        val sensorAccel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.unregisterListener(drawView, sensorAccel)
    }
}
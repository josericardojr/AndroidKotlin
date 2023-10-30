package com.example.sensorball

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.view.View
import androidx.core.content.ContextCompat

class DrawView : View, SensorEventListener {
    lateinit var position : PointF
    lateinit var paint : Paint
    var xAccel = 0.0f
    var yAccel = 0.0f
    var zAccel = 0.0f
    final val radius = 100.0f


    constructor(context: Context) : super(context)

    init {
        position = PointF(0.0f, 0.0f)
        paint = Paint()
        paint.color = Color.RED
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = canvas.width / 2
        val centerY = canvas.height / 2

        position.x = centerX - xAccel * (canvas.width / 20.0f)
        position.y = centerY + yAccel * (canvas.height / 20.0f)

        canvas.drawCircle(position.x, position.y, radius, paint)

    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER){
            xAccel = event?.values?.get(0) ?: 0.0f
            yAccel = event?.values?.get(1) ?: 0.0f
            zAccel = event?.values?.get(2) ?: 0.0f
        }

        invalidate()
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
}
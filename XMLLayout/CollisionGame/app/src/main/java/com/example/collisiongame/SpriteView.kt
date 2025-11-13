package com.example.collisiongame

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.scale

class Sprite(val image: Bitmap, val speed: Float) {

    var position: PointF
    var destination: PointF


    init {
        position = PointF(0f, 0f)
        destination = PointF(0f, 0f)
    }

    fun draw(canvas: Canvas?){
        canvas?.drawBitmap(image, position.x, position.y, null)
    }

    fun update() {
        val dir = PointF(
            destination.x - position.x,
            destination.y - position.y
        )

        val len = dir.length()
        dir.x /= len
        dir.y /= len

        val velocity = if (speed > len)
            (len / speed)
        else speed

        if (len < 0.01f){
            position.x = destination.x
            position.y = destination.y
        } else {
            position.x += dir.x * velocity
            position.y += dir.y * velocity
        }

    }

    fun moveTo(point: PointF){
        destination = point
    }
}
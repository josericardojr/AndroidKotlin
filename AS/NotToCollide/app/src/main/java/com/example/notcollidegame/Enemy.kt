package com.example.notcollidegame

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.PointF

data class Enemy(var position : PointF, val bmp : Bitmap){

    fun draw(canvas: Canvas){
        canvas.drawBitmap(bmp, position.x, position.y, null)
    }

    fun move(deltaX : Float){
        position.x += deltaX
    }

    fun respawn(maxWidth : Int, maxHeight : Int){
        position.x = maxWidth.toFloat()
        position.y = (Math.random() * (maxHeight - bmp.height).toFloat()).toFloat()
    }
}

package com.josericardo_jr.canvasapp

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.scale

class SpriteView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    constructor(context: Context) : this(context, null)

    lateinit var image: Bitmap
    var positionX = 0.0f

    init {
       val img = BitmapFactory.decodeResource(resources, R.drawable.dracula)
        image = img.scale(600, 600, false)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawBitmap(image, positionX, 0.0f, null)
    }
}
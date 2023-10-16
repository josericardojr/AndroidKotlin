package com.example.singletouch

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.MotionEvent
import android.view.View

/*
View customizada para desenho personalizado
 */
class DrawView : View, View.OnTouchListener {
    var posX = 0.0f
    var posY = 0.0f
    var color = Color.RED

    constructor(context : Context) : super(context) {
        // Setar o listener do evento de touch
        setOnTouchListener(this)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // Objeto para configurar opcoes de desenho
        val paint = Paint()
        paint.color = color

        canvas?.drawCircle(posX, posY, 200.0f, paint)
    }

    // Implementacao do comportamento ao gerar o evento de touch
    override fun onTouch(view: View?, motionEvent: MotionEvent?): Boolean {

        when (motionEvent?.action){
            MotionEvent.ACTION_DOWN -> {
                Log.i("CUSTOMVIEW", "Posicao X: ${motionEvent?.x} / Posicao Y: ${motionEvent?.y}")
                posX = motionEvent?.x
                posY = motionEvent?.y
                color = Color.RED
                invalidate()
            }

            MotionEvent.ACTION_MOVE -> {
                posX = motionEvent?.x
                posY = motionEvent?.y
                invalidate()
            }

            MotionEvent.ACTION_UP -> {
                color = Color.BLUE
                invalidate()
            }
        }

        return true
    }
}
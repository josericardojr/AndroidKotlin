package com.example.singletouch

import android.content.Context
import android.graphics.*
import android.util.Log
import android.view.MotionEvent
import android.view.View

/*
View customizada para desenho personalizado
 */

data class Line(var listOfPoints : List<PointF>){

    fun draw(canvas: Canvas?, paint: Paint){

        var i = 0
        while (i < listOfPoints.size - 1){
            val p0 = listOfPoints.get(i)
            val p1 = listOfPoints.get(i+1)

            canvas?.drawLine(p0.x, p0.y, p1.x, p1.y, paint)
            i++
        }

    }
}

class DrawView : View, View.OnTouchListener {
    var color = Color.RED

    // Array para armazenar os pontos desenhados na tela
    var listOfPoints = mutableListOf<PointF>()

    // Array de linhas desenhadas
    var listOfLines = mutableListOf<Line>()

    constructor(context : Context) : super(context) {
        // Setar o listener do evento de touch
        setOnTouchListener(this)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // Objeto para configurar opcoes de desenho
        val paint = Paint()
        paint.strokeWidth = 7.0f
        paint.color = color

        // Desenhar linhas finalizadas
        for (line in listOfLines)
            line.draw(canvas, paint)

        // Desenhar linha em construcao
        var i = 0
        while (i < listOfPoints.size - 1){
            val p0 = listOfPoints.get(i)
            val p1 = listOfPoints.get(i+1)

            canvas?.drawLine(p0.x, p0.y, p1.x, p1.y, paint)
            i++
        }

    }

    // Implementacao do comportamento ao gerar o evento de touch
    override fun onTouch(view: View?, motionEvent: MotionEvent?): Boolean {

        when (motionEvent?.action){
            MotionEvent.ACTION_DOWN -> {
                val point = PointF(motionEvent?.x, motionEvent?.y)
                Log.i("SINGLETOUCH", "ACTION_DOWN")
                listOfPoints.add(point)
            }

            MotionEvent.ACTION_MOVE -> {
                val point = PointF(motionEvent?.x, motionEvent?.y)
                listOfPoints.add(point)
                Log.i("SINGLETOUCH", "ACTION_MOVE")
                invalidate()
            }

            MotionEvent.ACTION_UP -> {
                val newLine = Line(listOfPoints.toList())
                listOfLines.add(newLine)

                listOfPoints.clear()
            }
        }
        return true
    }
}
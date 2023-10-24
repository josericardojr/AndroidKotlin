package com.example.circlemultitouch

import android.content.Context
import android.graphics.*
import android.util.Log
import android.view.MotionEvent
import android.view.View

/*
View customizada para desenho personalizado
 */


class DrawView : View, View.OnTouchListener {
    val circleRadius = 100.0f

    val colors =
        arrayOf(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW, Color.CYAN)

    // Especifica qual circulo esta ativo
    var circleEnabled = Array<Boolean>(5){false}
    var positionCircle = Array<PointF>(5){ PointF(0.0f, 0.0f) }


    constructor(context : Context) : super(context) {
        // Setar o listener do evento de touch
        setOnTouchListener(this)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val paint = Paint()
        paint.strokeWidth = 7.0f

        var index = 0;
        while (index < circleEnabled.size){
            if (circleEnabled[index]){
                paint.color = colors[index]
                val position = positionCircle[index]
                canvas?.drawCircle(position.x, position.y, circleRadius, paint)
            }

            index++
        }
    }

    // Implementacao do comportamento ao gerar o evento de touch
    override fun onTouch(view: View?, motionEvent: MotionEvent?): Boolean {

        var pointerIndex = motionEvent?.actionIndex ?: -1
        var pointerId = motionEvent?.getPointerId(pointerIndex) ?: -1

        when (motionEvent?.actionMasked){

            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
                circleEnabled[pointerId] = false
                invalidate()
            }



            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {

                if (pointerId < circleEnabled.size){
                    circleEnabled[pointerId] = true
                    positionCircle[pointerId].x = motionEvent.getX(pointerIndex)
                    positionCircle[pointerId].y = motionEvent.getY(pointerIndex)
                }
                invalidate()
            }

            MotionEvent.ACTION_MOVE -> {

                var index = 0
                while (index < motionEvent.pointerCount){

                    if (index < circleEnabled.size) {
                        var _pointId = motionEvent.getPointerId(index)
                        positionCircle[_pointId].x = motionEvent.getX(index)
                        positionCircle[_pointId].y = motionEvent.getY(index)
                    }

                    index++
                }
                invalidate()
            }
        }
        return true
    }
}
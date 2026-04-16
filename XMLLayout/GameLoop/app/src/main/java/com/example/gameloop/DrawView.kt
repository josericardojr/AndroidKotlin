package com.example.gameloop

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.graphics.PointF
import android.util.AttributeSet
import android.view.SurfaceView
import androidx.annotation.AttrRes
import kotlinx.coroutines.delay

class DrawView : SurfaceView, Runnable {
    var position: PointF = PointF()
    var painter: Paint
    var isGameStarted : Boolean = false
        private set

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)

    init {
        position.x += 100f
        position.y = 100f

        painter = Paint()
        painter.color = Color.RED

        setOnClickListener {
            position.x += 100
        }
    }

    fun startGame(){
        isGameStarted = true
        Thread(this).start()
    }

    fun stopGame(){
        isGameStarted = false
    }

    fun update(){
    }

    fun render(){

        if (!holder.surface.isValid)
            return

        val canvas = holder.lockCanvas()
        canvas.drawColor(Color.WHITE)
        canvas.drawCircle(position.x, position.y, 100f, painter)
        holder.unlockCanvasAndPost(canvas)
    }

    override fun run() {
        while (isGameStarted) {
            update()
            render()
            Thread.sleep(100)
        }
    }
}
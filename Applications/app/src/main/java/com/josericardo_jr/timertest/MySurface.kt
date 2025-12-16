package com.josericardo_jr.timertest

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.SurfaceView
import android.view.View
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Timer
import kotlin.concurrent.schedule
import kotlin.concurrent.timer

class MySurface(context: Context, attrs: AttributeSet? = null) : SurfaceView(context, attrs), View.OnClickListener{

    lateinit var myTimer : Timer
    var time = 30
    var paint = Paint()

    init {
        myTimer = timer(period = 1000) {
            time -= 1
            draw()
        }
        setOnClickListener(this)

        paint.color = Color.RED
        paint.textSize = 50f
    }

    fun draw(){
        val canvas = holder.lockCanvas()

        if (canvas == null)
            return

        canvas.drawColor(Color.WHITE)
        canvas.drawText("Tempo: $time", 100f, 300f, paint)

        holder.unlockCanvasAndPost(canvas)
    }

    fun restart(){
        myTimer.cancel()
        time = 30

        myTimer = timer(period = 1000){
            draw()
            time-=1
        }
    }

    override fun onClick(v: View?) {
    }
}
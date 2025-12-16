package com.example.collisiongame

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceView
import android.view.View
import androidx.core.graphics.scale

class GameView(context: Context, attr: AttributeSet?) : SurfaceView(context, attr),
    View.OnTouchListener
{
    var sprite: Sprite

    constructor(context: Context) : this(context, null)

    init {
        val image = BitmapFactory.decodeResource(resources,
            R.drawable.dracula)
        sprite = Sprite(image.scale(200, 200), 5f)

        setOnTouchListener(this)
    }



    fun render(){
        val canvas : Canvas? = holder.lockCanvas()
        canvas?.drawColor(Color.WHITE)
        sprite.draw(canvas)

        if (canvas!= null) holder.unlockCanvasAndPost(canvas)
    }

    fun update(){
        sprite.update()
    }

    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {

        if (p1?.action == MotionEvent.ACTION_DOWN){
            sprite.moveTo(
                PointF(p1?.x ?: 0f, p1?.y ?: 0f)
            )
        }

        return true;
    }
}
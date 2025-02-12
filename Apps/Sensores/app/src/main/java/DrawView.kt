import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.view.SurfaceView

class DrawView(context: Context) : SurfaceView(context),
    SensorEventListener {
    private var xAccel: Float = 0f
    private var yAccel: Float = 0f
    private var zAccel: Float = 0f


    override fun onSensorChanged(event: SensorEvent?) {

        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            xAccel = event.values[0]
            yAccel = event.values[1]
            zAccel = event.values[2]
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    fun render() {
        if (holder.surface?.isValid == false) return

        val canvas: Canvas = holder.lockCanvas()
        canvas.drawColor(Color.WHITE)

        val paint = Paint()
        paint.color = Color.BLUE

        val centerx = canvas.width / 2.0f
        val centery = canvas.height / 2.0f

        val ratioX = canvas.height / 10.0f
        val ratioY = canvas.height / 10.0f

        val x = centerx - xAccel * ratioX
        val y = centery + yAccel * ratioY

        canvas.drawCircle(x, y, 100f, paint)

        holder.unlockCanvasAndPost(canvas)
    }

}
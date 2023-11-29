package com.example.notcollidegame

import android.content.Context
import android.content.SharedPreferences
import android.graphics.*
import android.graphics.fonts.Font
import android.media.AudioAttributes
import android.media.SoundPool
import android.view.MotionEvent
import android.view.View

class DrawView(context: Context) : View(context) {

    final val GRAVITY = 1.5f
    final val IMPULSE = -15f
    final val FILE_NAME = "APPINFO"

    lateinit var bmp : Bitmap
    var position = PointF(0f,0f)
    var velocity = PointF()

    // Inimigo
    lateinit var enemy : Enemy

    // Sounds
    var explosion_enemy_sound : Int = 0

    lateinit var soundPool: SoundPool

    // Pontuacao do jogador
    private var score : Int = 0

    // Paint para armazenar informacoes do texto
    private val textPaint = Paint()

    init {
        // Carregar a imagem do jogador
        var bmp_ = BitmapFactory.decodeResource(resources, R.drawable.airplane)
        bmp = Bitmap.createScaledBitmap(bmp_, 240, 200, true)

        // Criar o objeto inimigo
        val bmpEnemy = Bitmap.createScaledBitmap(
            BitmapFactory.decodeResource(resources, R.drawable.enemy),
            180, 200, true)

        val posY = Math.random() * height
        enemy = Enemy(PointF((1920 - bmpEnemy.width).toFloat(), posY.toFloat()),bmpEnemy)



        setOnTouchListener { view, motionEvent ->

            if (motionEvent.action == MotionEvent.ACTION_DOWN){
                velocity.y = IMPULSE
            }

            return@setOnTouchListener true
        }
        // Carregar efeitos
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build()

        soundPool = SoundPool.Builder()
            .setAudioAttributes(audioAttributes)
            .setMaxStreams(2)
            .build()

        explosion_enemy_sound = soundPool.load(context, R.raw.explosion_enemy, 0)

        // Carregar SharedPreferences para armazenar a pontuacao

        // Setar informacoes do texto
        textPaint.textSize = 60f
        textPaint.color = Color.WHITE

        // Carregar score salvo
        val sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        score = sharedPreferences.getInt("Score", 0)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bmp, position.x, position.y, null)
        enemy.draw(canvas)

        velocity.y = Math.min(velocity.y + GRAVITY, 15.0f)
        position.y += velocity.y

        if (position.y < 0) position.y = 0f

        if (position.y + bmp.height > canvas.height)
            position.y = (canvas.height - bmp.height).toFloat()

        enemy.move(-5f)

        // Verificar se o inimigo saiu da tela a fim de reposicionar o mesmo
        if (enemy.position.x + enemy.bmp.width < 0) {
            enemy.respawn(canvas.width, canvas.height)
            score += 1
        }

        // Verificar se o jogador colidiu com o inimigo
        if (checkCollision(position, bmp.width, bmp.height,
            enemy.position, enemy.bmp.width, enemy.bmp.height)){
            soundPool.play(explosion_enemy_sound, 1f, 1f, 0, 0, 1f)
            enemy.respawn(canvas.width, canvas.height)

            score = 0
        }

        val textScore = "Score: ${score}"
        val _sizeScore = textPaint.measureText(textScore)

        canvas.drawText(textScore, canvas.width - _sizeScore, 60f, textPaint)

        invalidate()
    }

    private fun checkCollision(posObj1 : PointF, w1 : Int, h1 : Int,
        posObj2 : PointF, w2 : Int, h2 : Int) : Boolean {

        val rect1 = Rect(posObj1.x.toInt(), posObj1.y.toInt(),
            (posObj1.x + w1).toInt(), (posObj1.y + h1).toInt())

        val rect2 = Rect(posObj2.x.toInt(), posObj2.y.toInt(),
            (posObj2.x + w2).toInt(), (posObj2.y + h2).toInt())

        return rect1.intersect(rect2)
    }

    /*
    Salvar score em arquivo
     */
    public fun saveData(){
        val sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putInt("Score", score)
        editor.commit()
    }
}
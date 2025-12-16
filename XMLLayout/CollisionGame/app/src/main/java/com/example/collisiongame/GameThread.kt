package com.example.collisiongame

class GameThread(val gameView: GameView) : Thread() {

    var running = false
    lateinit var gameThread: Thread


    // Inicia o game loop
    fun startGame(){
        running = true
        gameThread = Thread(this)
        gameThread.start()
    }

    // Finaliza o game loop
    fun stopGame(){
        running = false
    }

    override fun run() {
        while (running){
            update()
            render()
            sleep(32)
        }
    }

    // Atualizacao dos elementos (Logica do jogo)
    fun update(){
        gameView.update()
    }

    // Desenho dos elementos na tela
    fun render() {
        gameView.render()
    }
}
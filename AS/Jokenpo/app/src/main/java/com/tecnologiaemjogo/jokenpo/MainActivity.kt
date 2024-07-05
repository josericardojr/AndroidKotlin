package com.tecnologiaemjogo.jokenpo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import com.tecnologiaemjogo.jokenpo.databinding.MainActivityBinding

class MainActivity : ComponentActivity() {

    // Allowed play options that can be performed during the game
    enum class PlayOption {
        Scissor,
        Rock,
        Paper,
    }

    // Automatically performs a binding on widget components from the layout file
    private lateinit var binding : MainActivityBinding

    // Game State
    private var currentRound = 0
    private var playerScore = 0
    private var opponentScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflates the layout
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)

        // Reset the game
        resetGame()

        // Button used for reset the score
        binding.btnReiniciar.setOnClickListener {
            resetGame()
            updateInterface()
        }

    }

    /**
     * Method used for reset the game
     */
    private fun resetGame(){
        currentRound = 0
        playerScore = 0
        opponentScore = 0

        configureGameAction { playerOption ->
            val opponentOption = makeOpponentPlay()

            // Check if player wins
            if (checkVictory(playerOption, opponentOption)){
                playerScore++
            } else
                // Check if opponent wins
                if (checkVictory(opponentOption, playerOption)){
                opponentScore++
            }

            // Increase the round
            currentRound++

            // Update the interface
            updateInterface()
        }

        // Set the image of opponent move back to default
        binding.jogadaOponente.setImageResource(R.drawable.padrao)

    }

    /***
     * Method used to update the interface after game state variables are changed
     */
    private fun updateInterface(){
        binding.txtPlacarAdversario.text = opponentScore.toString()
        binding.txtPlacarJogador.text = playerScore.toString()
        binding.txtRodada.text = currentRound.toString()
    }

    /***
     * Method called for checking a winner
     * @param player the movement to check for winner
     * @param opponent the movement of the opponent
     * @return True in case the the player wins or false otherwise
     */
    private fun checkVictory(
        player: PlayOption,
        opponent: PlayOption
    ): Boolean {

        val result =
            when (player) {
                PlayOption.Rock ->
                    opponent == PlayOption.Scissor

                PlayOption.Scissor ->
                    opponent == PlayOption.Paper

                PlayOption.Paper ->
                    opponent == PlayOption.Rock

                else ->
                    false
            }

        return result
    }

    /***
     * Method used in order to make the opponent move after the player play
     */
    private fun makeOpponentPlay(): PlayOption {
        val option = PlayOption.values().toList().shuffled().first()

        when(option){
            PlayOption.Rock ->
                binding.jogadaOponente.setImageResource(R.drawable.pedra)
            PlayOption.Paper ->
                binding.jogadaOponente.setImageResource(R.drawable.papel)
            PlayOption.Scissor ->
                binding.jogadaOponente.setImageResource(R.drawable.tesoura)
        }

        return option
    }

    /***
     * Method used for configuring the behavior of the buttons
     * the Player use to make a move
     * @param onClick action performed when the player selects an option
     */
    private fun configureGameAction(
        onClick: (PlayOption) -> Unit
    ){
        // Paper button
        binding.btnPapel.setOnClickListener {
            onClick(PlayOption.Paper)
        }

        // Rock button
        binding.btnPedra.setOnClickListener {
            onClick(PlayOption.Rock)
        }

        // Scissor button
        binding.btnTesoura.setOnClickListener {
            onClick(PlayOption.Scissor)
        }
    }
}


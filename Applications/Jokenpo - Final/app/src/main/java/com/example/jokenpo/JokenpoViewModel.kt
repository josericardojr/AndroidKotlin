package com.example.jokenpo

import android.app.Application
import android.media.SoundPool
import androidx.annotation.DrawableRes
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class GameUiState(
    val placarJogador: Int = 0,
    val placarAdversario: Int = 0,
    @DrawableRes val enemyImage: Int = R.drawable.padrao
)

class JokenpoViewModel(application: Application) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState = _uiState.asStateFlow()

    private var soundPool : SoundPool

    // Id do som de efeito
    private var soundIdEffect = 0

    init {
        soundPool = SoundPool.Builder()
            .setMaxStreams(2)
            .build()

        soundIdEffect = soundPool.load(
            application.applicationContext,
            R.raw.option_selection,
            1)

        resetGame()
    }

    /**
     * Metodo chamado quando o jogador realiza uma jogada.
     */
    fun onPlayerMakeOption(playOption: PlayOption) {
        soundPool.play(soundIdEffect, 1.0f, 1.0f, 0, 0, 1.0f)

        // Realizar a jogada do adversário
        val enemyOption = makeOpponentPlay()

        // Atualizar a imagem da jogada do adversario
        _uiState.value = _uiState.value.copy(
            enemyImage = when (enemyOption) {
                PlayOption.Pedra -> R.drawable.pedra
                PlayOption.Papel -> R.drawable.papel
                PlayOption.Tesoura -> R.drawable.tesoura
            }
        )

        // Verificar o vencedor
        if (checkVictory(playOption, enemyOption)) {
            _uiState.value = _uiState.value.copy(
                placarJogador = _uiState.value.placarJogador + 1
            )
        } else if (checkVictory(enemyOption, playOption)) {
            _uiState.value = _uiState.value.copy(
                placarAdversario = _uiState.value.placarAdversario + 1
            )
        }
    }

    fun resetGame() {
        _uiState.value = GameUiState()
    }

    /**
     * Metodo chamado para fazer a jogada do adversario.
     */
    private fun makeOpponentPlay(): PlayOption {
        val option = PlayOption.entries.toList().shuffled().first()

        return option
    }


    // Metodo chamado para verificar a vitoria do jogador.
    private fun checkVictory(
        option1: PlayOption,
        option2: PlayOption
    ): Boolean {

        val result =
            when (option1) {
                PlayOption.Pedra ->
                    option2 == PlayOption.Tesoura

                PlayOption.Tesoura ->
                    option2 == PlayOption.Papel

                PlayOption.Papel ->
                    option2 == PlayOption.Pedra

                else ->
                    false
            }

        return result
    }

    override fun onCleared() {
        super.onCleared()
        soundPool.release()
    }
}
package com.josericardo_jr.tapthemole.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

const val TIMER_GAME = 10
const val INTERVAL = 2000L

data class MoleState(val isVisible: Boolean = false, val isHit: Boolean = false)
data class GameState(val score: Int = 0, val timer: Int = TIMER_GAME, val isGameOver: Boolean = false,
                     val moles: List<MoleState> = emptyList())

class MainActivityViewModel : ViewModel() {

    private val _moleState = MutableStateFlow(GameState())

    val moleState: StateFlow<GameState> = _moleState.asStateFlow()

    init {
        resetGame()
    }

    fun resetGame() {

        _moleState.value = GameState(
            timer = TIMER_GAME,
            moles = List<MoleState>(9) { MoleState() }
        )

        viewModelScope.launch (Dispatchers.Default) {

            timerCountDown()

            while (!_moleState.value.isGameOver) {

                    delay(INTERVAL)


                _moleState.update { currentState ->
                    val listOfMoles = currentState.moles.toMutableList()

                    val randomIndex = Random.nextInt(listOfMoles.size)

                    for (i in listOfMoles.indices) {
                        listOfMoles[i] = if (i == randomIndex)
                            MoleState(isVisible = true)
                        else
                            MoleState(isVisible = false)
                    }

                    currentState.copy(
                        moles = listOfMoles.toList()
                    )
                }
            }
        }
    }

    private fun timerCountDown() {
        viewModelScope.launch (Dispatchers.Default) {
            while (moleState.value.timer > 0) {
                delay(1000)

                val currentTime = _moleState.value.timer - 1
                val isGameOver = currentTime == 0

                _moleState.update { currentState ->
                    currentState.copy(
                        timer = currentTime,
                        isGameOver = isGameOver
                    )
                }
            }
        }
    }

    fun onClick() {
        _moleState.update { currentState ->
            currentState.copy(
                score = currentState.score + 1
            )
        }
    }
}
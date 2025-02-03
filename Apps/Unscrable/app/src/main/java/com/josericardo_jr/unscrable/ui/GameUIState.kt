package com.josericardo_jr.unscrable.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

data class GameUIState (
    var currentScrambleWord : String = "", // Palavra atual
    val isGameOver : Boolean = false, // Fim de Jogo?
    val score: Int = 0, // Pontuação
    val currentWordCount : Int = 1, // Total de Palavras jogadas
    val userGuess: String = "", // Armazena o chute atual do jogador
    var isGuessWrong: Boolean = false, // Erro de chute
)
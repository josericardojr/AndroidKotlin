package com.josericardo_jr.unscrable.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.josericardo_jr.unscrable.data.MAX_NO_OF_WORDS
import com.josericardo_jr.unscrable.data.SCORE_INCREASE
import com.josericardo_jr.unscrable.data.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {

    // Palavra atual
    private lateinit var currentWord : String

    // Variável que irá armazenar e comunicar a mudança de estados
    private val _currentGameState = MutableStateFlow (GameUIState())

    // Propriedade que e exposta para evitar atualizações externas
    val currentGameState : StateFlow<GameUIState> = _currentGameState.asStateFlow()

    init {
        resetGame()
    }

    fun resetGame() {
        _currentGameState.value = GameUIState(
            currentScrambleWord = getRandomdScrambledWord()
        )
    }


    /**
     * Recuperar uma palavra aleatória do conjunto de palavras e embaralhar
     */
    private fun getRandomdScrambledWord() : String {
        currentWord = allWords.random()

        val tempWord = currentWord.toCharArray().apply { shuffle() }

        // Verificar se a palavra embaralhada é igual a palavra original
        while (String(tempWord).equals(currentWord, true)) {
            tempWord.shuffle()
        }

        return String(tempWord)
    }

    /**
     * Metodo chamado quando o jogador digita na caixa de texto
     */
    fun updateUserGuess(guessedWord : String) {
        _currentGameState.update { currentState ->
            currentState.copy(
                userGuess = guessedWord
            )
        }
    }

    /**
     * Verificar se a resposta do jogador é correta.
     */
    fun checkAnswer(){
        if (currentWord.equals(_currentGameState.value.userGuess, ignoreCase = true)) {
            val score = _currentGameState.value.score.plus(SCORE_INCREASE)

            updateGameState(score)

        } else {
            _currentGameState.update {
                currentState -> currentState.copy(
                    isGuessWrong = true
                )
            }
        }
    }

    /**
    * Função responsável por atualizar o estado com a nova pontuação e verificar se
    * atingimos o número máximo de palavras
    */
    private fun updateGameState(score: Int){
        // Próxima palavra
        val wordCount = _currentGameState.value.currentWordCount.inc()

        // Verificar se atingimos o número maximo de palavras
        if (wordCount < MAX_NO_OF_WORDS) {
            _currentGameState.update { currentGameState ->
                currentGameState.copy(
                    currentScrambleWord = getRandomdScrambledWord(),
                    userGuess = "",
                    score = score,
                    isGuessWrong = false,
                    currentWordCount = wordCount
                )
            }
        } else {
            _currentGameState.update { currentGameState ->
                currentGameState.copy(
                    isGameOver = true
                )
            }
        }
    }

    /**
     * Função que pula uma palavra durante o jogo
     */
    fun skipWord() {
        updateGameState(_currentGameState.value.score)
    }

}
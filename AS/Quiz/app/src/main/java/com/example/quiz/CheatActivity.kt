package com.example.quiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

const val ANSWER_KEY = "Resposta_Correta"
const val SHOWN_ANSWER = "ANSWER_SHOWN"

class CheatActivity : AppCompatActivity() {

    private var answerQuestion : Boolean = false
    private lateinit var btnExibirResposta : Button
    private lateinit var viewAnswer : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        answerQuestion = intent.getBooleanExtra(ANSWER_KEY, false)

        // Recuperar elementos
        viewAnswer = findViewById(R.id.viewAnswer)
        btnExibirResposta = findViewById(R.id.btnExibirResposta)

        // Comportamento do Botao
        btnExibirResposta.setOnClickListener {
            val answer = when {
                answerQuestion -> "Verdadeiro"
                else -> "Falso"
            }

            viewAnswer.text = answer
            setAnswerShownResult(true)
        }
    }

    private fun setAnswerShownResult(answerShown : Boolean){
        val data = Intent().apply {
            putExtra(SHOWN_ANSWER, answerShown)
        }

        // Resultado da Activity
        setResult(Activity.RESULT_OK, data)
    }
}
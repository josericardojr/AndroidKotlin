package com.example.quiz

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import java.net.URL

class MainActivity : AppCompatActivity() {

    // Todo Criar a listagem de questoes

    // Botão de resposta verdadeira
    private lateinit var trueButton : Button
    // Botão de resposta false
    private lateinit var falseButton : Button
    // Botão que avança para a próxima questão
    private lateinit var nextQuestion: Button
    // Botao de trapaca!
    private lateinit var cheatButton: Button
    // Texto que exibe a questao
    private lateinit var questionView : TextView
    // Questao atual
    private var currentQuestion = 0
    // Trapaceou?
    private var cheated : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.btnTrue)
        falseButton = findViewById(R.id.btnFalse)
        nextQuestion = findViewById(R.id.btnNextButton)
        questionView = findViewById(R.id.questionTextView)
        cheatButton = findViewById(R.id.btnCheat)


        // Todo Tratamento do evento no botão verdadeiro
        trueButton.setOnClickListener {
        }

        // Todo Tratamento do evento no botão falso
        falseButton.setOnClickListener {
        }

        // Todo Tratamento do evento para avancar a proxima questao
        nextQuestion.setOnClickListener {
        }

        // Todo Tratamento do evento para trapaca
        cheatButton.setOnClickListener {
        }

    }
}
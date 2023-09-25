package com.example.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val questionBank = listOf<Question>(
        Question(R.string.question_orca, true),
        Question(R.string.question_pe, false),
        Question(R.string.question_espirro, false),
        Question(R.string.question_dente, true)
    )

    // Botão de resposta verdadeira
    private lateinit var trueButton : Button
    // Botão de resposta false
    private lateinit var falseButton : Button
    // Botão que avança para a próxima questão
    private lateinit var nextQuestion: Button
    // Texto que exibe a questao
    private lateinit var questionView : TextView
    // Questao atual
    private var currentQuestion = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.btnTrue)
        falseButton = findViewById(R.id.btnFalse)
        nextQuestion = findViewById(R.id.btnNextButton)
        questionView = findViewById(R.id.questionTextView)

        // Tratamento do evento no botão verdadeiro
        trueButton.setOnClickListener {

        }

        // Tratamento do evento no botão falso
        falseButton.setOnClickListener {

        }

        // Tratamento do evento para avancar a proxima questao
        nextQuestion.setOnClickListener {
            currentQuestion = (currentQuestion + 1) % questionBank.size
            updateQuestion()
        }

        // Atualizar para a primeira questao do banco
        updateQuestion()

        Log.i("Estados", "onCreate() chamado")
    }

    override fun onStart() {
        super.onStart()
        Log.i("Estados", "onStart() chamado")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Estados", "onResume() chamado")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Estados", "onPause() chamado")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Estados", "onStop() chamado")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Estados", "onDestroy() chamado")
    }

    /*
    Metodo para atualizar o texto da questao
     */
    private fun updateQuestion(){
        val questionTextId = questionBank[currentQuestion].resId
        questionView.setText(questionTextId)
    }

}
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
    // Botao de trapaca!
    private lateinit var cheatButton: Button
    // Texto que exibe a questao
    private lateinit var questionView : TextView
    // Questao atual
    private var currentQuestion = 0
    // Trapaceou?
    private var cheated : Boolean = false

    // Chamada da activity cheat com resultado da interacao
    private val cheatLaucher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            cheated = result.data?.getBooleanExtra(SHOWN_ANSWER, false) ?: false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.btnTrue)
        falseButton = findViewById(R.id.btnFalse)
        nextQuestion = findViewById(R.id.btnNextButton)
        questionView = findViewById(R.id.questionTextView)
        cheatButton = findViewById(R.id.btnCheat)


        // Tratamento do evento no botão verdadeiro
        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        // Tratamento do evento no botão falso
        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        // Tratamento do evento para avancar a proxima questao
        nextQuestion.setOnClickListener {
            currentQuestion = (currentQuestion + 1) % questionBank.size
            updateQuestion()
        }

        // Tratamento do evento de verificar a resposta correta
        cheatButton.setOnClickListener {
            val intent = Intent(this, CheatActivity::class.java)
            intent.putExtra(ANSWER_KEY, questionBank[currentQuestion].answer)
            cheatLaucher.launch(intent)
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
        cheated = false
    }

    private fun checkAnswer(userAnswer : Boolean){
        val correctAnswer = questionBank[currentQuestion].answer

        val messageId = when {
            cheated -> R.string.cheated
            userAnswer == correctAnswer -> R.string.correct_answer
            else -> R.string.wrong_answer
        }

        Toast.makeText(this, messageId, Toast.LENGTH_SHORT)
            .show()
    }

}
package com.example.quiz

import androidx.annotation.StringRes

data class Question(@StringRes val resId : Int, val answer : Boolean)

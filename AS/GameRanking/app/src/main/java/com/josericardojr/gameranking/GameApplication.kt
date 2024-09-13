package com.josericardojr.gameranking

import android.app.Application

class GameApplication : Application() {
    val database by lazy { GameDatabase.getDatabase(this) }
}
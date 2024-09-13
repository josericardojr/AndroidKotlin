package com.josericardojr.gameranking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameViewModel(
    private val database: GameDatabase
) : ViewModel() {
    //
    private var _playerResult = MutableStateFlow(emptyList<Player>())
    val playerResult = _playerResult.asStateFlow()

    init {
        findPlayer()
    }

    fun findPlayer() = viewModelScope.launch {
        database.playerDao().getAllPlayers().flowOn(
            Dispatchers.IO
        ).collect { players: List<Player> ->
            _playerResult.update { players }
        }
    }

    fun findPlayer(nickname: String) = viewModelScope.launch {
        database.playerDao().getAllPlayers(nickname).flowOn(
            Dispatchers.IO
        ).collect { players: List<Player> ->
            _playerResult.update { players }
        }
    }

    fun insert(player: Player)  = viewModelScope.launch {
        database.playerDao().insert(player)
    }
}

class GameViewModelFactory(
    private val database: GameDatabase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel(database) as T
        }
        throw IllegalArgumentException("ViewModel class não reconhecida")
    }
}
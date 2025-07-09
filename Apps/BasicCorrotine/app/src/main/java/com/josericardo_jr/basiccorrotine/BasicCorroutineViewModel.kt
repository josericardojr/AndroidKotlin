package com.josericardo_jr.basiccorrotine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BasicCorroutineViewModel : ViewModel() {

    private val _texto = MutableStateFlow("Clique para carregar dados")
    val texto: StateFlow<String> = _texto.asStateFlow()

    private val _carregando = MutableStateFlow(false)
    val carregando: StateFlow<Boolean> = _carregando.asStateFlow()

    fun carregarDados() {
        if (_carregando.value) return

        _carregando.value = true
        viewModelScope.launch {
            _texto.value = "Carregando dados..."
            val dados = withContext(Dispatchers.Main) {
                delay(8000) // simula operação custosa
                "Dados recebidos do servidor!"
            }
            _texto.value = dados
            _carregando.value = false
        }
    }
}
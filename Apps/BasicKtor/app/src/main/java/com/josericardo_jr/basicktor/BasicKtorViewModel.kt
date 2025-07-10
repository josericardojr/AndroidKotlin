package com.josericardo_jr.basicktor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BasicKtorViewModel : ViewModel() {
    private val _text = MutableStateFlow("Clique para carregar o post")
    val text = _text.asStateFlow()

    private val _carregando = MutableStateFlow(false)
    val carregando = _carregando.asStateFlow()


    fun carregarPost() {
        if (_carregando.value) return

        _carregando.value = true

        viewModelScope.launch {
            try {
                val post = KtorClient.getPost(1)
                _text.value = post.toString()
            } catch (e: Exception) {
                _text.value = e.localizedMessage ?: "error"
            } finally {
                _carregando.value = false
            }
        }
    }

}
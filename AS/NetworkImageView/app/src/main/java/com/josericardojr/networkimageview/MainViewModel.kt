package com.josericardojr.networkimageview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josericardojr.networkimageview.datamodel.PixabayResult
import com.josericardojr.networkimageview.network.PixabayApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val pixabeykey = "YOUR_KEY"
    private val _newData : MutableStateFlow<PixabayResult> = MutableStateFlow(PixabayResult())
    val newData : StateFlow<PixabayResult> get() = _newData.asStateFlow()

    fun getPhotos(){

        viewModelScope.launch {
            val res = PixabayApi.retrofitService.getPhotos(
                apiKey = pixabeykey,
                query = "Sonic game"
            )
            _newData.update { currentData ->
                currentData.copy(
                    total = res.total,
                    totalHits = res.totalHits,
                    hits = res.hits
                    )
            }
        }
    }
}
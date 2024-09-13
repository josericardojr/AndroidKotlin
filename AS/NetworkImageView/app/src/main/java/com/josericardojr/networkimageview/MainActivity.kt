package com.josericardojr.networkimageview

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.math.min

class MainActivity : AppCompatActivity() {

    // Configurando qual será a ViewModel desta atividade
    private val viewModel : MainViewModel by viewModels()

    // Imagens a serem preenchidas
    lateinit var imgs : List<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)

        // Recuperando a referência das views da interface
        imgs = listOf(
            findViewById<ImageView>(R.id.img1),
            findViewById<ImageView>(R.id.img2),
            findViewById<ImageView>(R.id.img3)
        )

        // Aqui estamos realizando a coleta dos dados provenientes da rede em uma corotina.
        // Essa corotina somente é executada enquanto estivermos no método Start da Activity.
        // Ao sairmos desse método, a mesma é cancelada, o que garante que não fiquemos
        // processando dados quando esta activity não estiver mais visível para o usuário.
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.newData.collect { state ->
                    var totalImgs = min(state.hits.size, 3)

                    while (totalImgs > 0) {
                        // Selecionar uma imagem aleatória
                        val hit = state.hits.random()

                        // O método load foi adicionado à classe ImageView pela biblioteca
                        // coil
                        imgs[totalImgs-1].load(hit.webformatURL)
                        totalImgs--
                    }
                }
            }
        }

        findViewById<Button>(R.id.btnGet).setOnClickListener {
            viewModel.getPhotos()
        }

    }
}
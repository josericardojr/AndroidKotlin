package com.example.jokenpo.ui

import android.widget.Button
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.jokenpo.R


@Composable
fun RenderCanvas(
    modifier : Modifier = Modifier
){
    var image = ImageBitmap.imageResource(R.drawable.tesoura)
    var posX by remember { mutableStateOf(10.dp) }

    Column (
        modifier = modifier
            .padding(16.dp)
    ) {
        Text("Teste")

        BoxWithConstraints (
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            val tileSize = 40.dp

            val numQuads = this.maxWidth / tileSize

            Canvas(modifier = Modifier.fillMaxWidth()) {

                repeat(numQuads.toInt()) { x ->

                    val posX = tileSize * x

                    drawRect(
                        Color.Red,
                        topLeft = Offset(posX.toPx(), 20f),
                        size = Size(tileSize.toPx() - 10, tileSize.toPx())
                    )
                }

                translate (posX.toPx()) {
                    drawImage(
                        image
                    )
                }
            }
        }



        Button(onClick = {
            posX += 1.dp
        }) {
            Text("OK")
        }
    }


}

@Preview(showBackground = true)
@Composable
fun RenderCanvasPreview(){
    RenderCanvas(modifier = Modifier.fillMaxSize())
}
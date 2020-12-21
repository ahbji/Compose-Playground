package `in`.surajsau.compose.screens

import `in`.surajsau.compose.R
import `in`.surajsau.compose.ui.defaultIconColor
import `in`.surajsau.compose.ui.progressIconColor
import android.util.Log
import androidx.annotation.FloatRange
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize

@Composable
fun GenshinScreen() {
    val slider = remember { mutableStateOf(0f) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Simple",
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Spacer(modifier = Modifier.height(height = 16.dp))

        Text(
            text = "Canvas",
            modifier = Modifier.padding(bottom = 8.dp)
        )
        GenshinLoader(progress = slider.value)

        Slider(
            value = slider.value,
            valueRange = 0f..100f,
            onValueChange = {
                Log.e("Slider", "$it")
                slider.value = it
            },
            steps = 1,
            modifier = Modifier
                .width(width = 300.dp)
                .padding(top = 16.dp)
        )
    }
}

@Composable
fun GenshinLoader(
    @FloatRange(from = 0.0, to = 100.0) progress: Float
) {
    Row {
        listOf(
            R.drawable.anemo,
            R.drawable.cryo,
            R.drawable.dendro,
            R.drawable.electro,
            R.drawable.geo,
            R.drawable.hydro,
            R.drawable.pyro
        ).forEachIndexed { index, imageRes ->
            val from = 100f/7 * index
            val to = 100f/7 * (index + 1)
            val fraction = when {
                progress > to -> 1f
                progress > from -> (progress - from)/(to - from)
                else -> 0f
            }
            GenshinIcon(
                fraction = fraction,
                image = imageResource(id = imageRes),
                size = 50.dp
            )
        }
    }
}

@Composable
fun GenshinIcon(
    fraction: Float,
    image: ImageBitmap,
    defaultColor: Color = defaultIconColor,
    progressColor: Color = progressIconColor,
    size: Dp = image.width.dp
) {
    Box(modifier = Modifier.size(size = size)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawIntoCanvas {
                it.saveLayer(
                    bounds = Rect(offset = Offset.Zero,
                        size = Size(width = size.toPx(), height = size.toPx())
                    ),
                    paint = Paint()
                )
                val imagePaint = Paint().apply {
                    colorFilter = ColorFilter.tint(
                        color = defaultColor
                    )
                    blendMode = BlendMode.SrcOver
                }
                val paint = Paint().apply {
                    color = progressColor
                    blendMode = BlendMode.SrcIn
                }

                it.drawImageRect(
                    image = image,
                    paint = imagePaint,
                    dstSize = IntSize(
                        width = size.toIntPx(),
                        height = size.toIntPx()
                    )
                )

                it.drawRect(
                    rect = Rect(
                        offset = Offset.Zero,
                        size = Size(
                            width = size.toPx() * fraction,
                            height = size.toPx()
                        )
                    ),
                    paint = paint
                )
                it.restore()
            }
        }
    }
}

@Preview
@Composable
fun PreviewGenshinScreen() {
    GenshinScreen()
}
package `in`.surajsau.compose.ui.samples.genshin

import `in`.surajsau.compose.R
import `in`.surajsau.compose.ui.defaultIconColor
import `in`.surajsau.compose.ui.progressIconColor
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

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
                                width = size.toPx().toInt(),
                                height = size.toPx().toInt()
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
fun previewIcon() {
    Box(modifier = Modifier.size(120.dp).background(Color.White)) {
        GenshinIcon(
                fraction = 0.5f,
                image = ImageBitmap.imageResource(id = R.drawable.hydro),
                size = 100.dp
        )
    }
}
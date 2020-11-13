package `in`.surajsau.genshinloader.icon

import `in`.surajsau.genshinloader.ui.defaultIconColor
import `in`.surajsau.genshinloader.ui.progressIconColor
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.example.genshinloader.R

@Composable
fun GenshinIcon(
        fraction: Float,
        image: ImageAsset,
        defaultColor: Color = defaultIconColor,
        progressColor: Color = progressIconColor,
        size: Dp = image.width.dp
) {
    Box(modifier = Modifier.size(size = size)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawImage(
                    image = image,
                    dstSize = IntSize(
                            width = size.toIntPx(),
                            height = size.toIntPx()
                    ),
                    colorFilter = ColorFilter.tint(
                            color = defaultColor
                    ),
                    blendMode = BlendMode.Src
            )
            drawIntoCanvas {
                val paint = Paint().apply {
                    color = progressColor
                    blendMode = BlendMode.SrcIn
                }

                it.restore()
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
                it.save()
            }
        }
    }
}

@Preview
@Composable
fun previewIcon() {
    GenshinIcon(
            fraction = 0.5f,
            image = imageResource(id = R.drawable.hydro),
            size = 100.dp
    )
}
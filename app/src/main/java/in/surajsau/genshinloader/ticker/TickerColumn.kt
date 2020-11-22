package `in`.surajsau.genshinloader.ticker

import android.graphics.Rect
import android.graphics.Typeface
import androidx.compose.animation.core.TransitionState
import androidx.compose.animation.transition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview

@Composable
fun TickerColumn(state: TransitionState, digit: Int) {
    val paint = Paint().asFrameworkPaint().apply {
        textSize = 100.sp.value
        color = Color.Red.toArgb()
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    }
    val bounds = Rect()

    paint.getTextBounds("$digit", 0, 1, bounds)

    val height = bounds.height()
    val width = bounds.width()

    Canvas(modifier = Modifier.size(
            width = width.dp,
            height = height.dp
    )) {
        clipRect(
            top = 0f,
            left = 0f,
            right = width.dp.value,
            bottom = height.dp.value
        ) {
            drawIntoCanvas {
                val nativeCanvas = it.nativeCanvas
                nativeCanvas.drawText(
                    "${digit + 1}",
                    0f,
                    height * state[animationProgress],
                    paint
                )

                nativeCanvas.drawText(
                    "$digit",
                    0f,
                    height + height * state[animationProgress],
                    paint
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewTickerColumn() {
    val transition = transition(
            definition = valueAnimatorDefinition,
            initState = Progress.START,
            toState = Progress.END
    )

    Surface(color = Color.White) {
        TickerColumn(state = transition, digit = 0)
    }
}
package `in`.surajsau.compose.todo

import androidx.compose.animation.animatedFloat
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun TickAnimation(
        color: Color,
        headAnimation: Float,
        tailAnimation: Float,
        modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier
            .width(100.dp)
            .height(100.dp)
    ) {
        drawLine(
                color = color,
                start = Offset(
                        26.dp.toPx() + (4.dp.toPx() * headAnimation),
                        46.dp.toPx() + (4.dp.toPx() * headAnimation),
                ),
                end = Offset(
                        26.dp.toPx() + if(tailAnimation < 0.3) (8.dp.toPx() * tailAnimation * 3.33f) else 8.dp.toPx(),
                        46.dp.toPx() + if(tailAnimation < 0.3) (8.dp.toPx() * tailAnimation * 3.33f) else 8.dp.toPx()
                ),
                strokeWidth = 6f,
                cap = StrokeCap.Round
        )

        if(tailAnimation >= 0.3)
            drawLine(
                    color = color,
                    start = Offset(
                            34.dp.toPx(),
                            54.dp.toPx()
                    ),
                    end = Offset(
                            34.dp.toPx() + (12.dp.toPx() * (tailAnimation - 0.3f) * 1.43f),
                            54.dp.toPx() - (16.dp.toPx() * (tailAnimation - 0.3f) * 1.43f)
                    ),
                    strokeWidth = 6f,
                    cap = StrokeCap.Round
            )

        if(tailAnimation >= 0.3)
            (1..10).forEach {
                val angle = 2 * Math.PI * it/10
                drawCircle(
                        color = color,
                        center = Offset(
                                38.dp.toPx() + (27.dp.toPx() + (5.dp.toPx() * (tailAnimation - 0.3f) * 3.33f)) * sin(angle).toFloat(),
                                46.dp.toPx() + (27.dp.toPx() + (5.dp.toPx() * (tailAnimation - 0.3f) * 3.33f)) * cos(angle).toFloat()
                        ),
                        radius = 5f - (5f * (tailAnimation - 0.3f) * 3.33f)
                )
            }
    }
}

@Composable
@Preview
fun previewTickAnimation() {
    Box(modifier = Modifier.fillMaxSize()) {
        val head = animatedFloat(initVal = 0f)
        val tail = animatedFloat(initVal = 0f)
        TickAnimation(
                color = Color.Black,
                headAnimation = head.value,
                tailAnimation = tail.value,
                modifier = Modifier.align(alignment = Alignment.Center)
        )

        Button(onClick = {
            head.animateTo(1f, anim = tween(durationMillis = 500, easing = LinearEasing))
            tail.animateTo(1f, anim = tween(durationMillis = 500, easing = LinearEasing))
        }, modifier = Modifier.align(alignment = Alignment.BottomCenter)) {}
    }
}
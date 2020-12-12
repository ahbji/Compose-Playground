package `in`.surajsau.compose.todo

import androidx.compose.animation.animatedFloat
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun TickAnimation(
        color: Color,
        modifier: Modifier = Modifier,
        tickSize: Dp = 50.dp,
        sparkleRadius: Dp = 70.dp,
        strokeWidth: Float = 6f,
        headAnimation: Float,
        tailAnimation: Float,
) {
    Canvas(modifier = modifier
            .size(size = sparkleRadius)
    ) {
        val width = size.width
        val height = size.height

        val tickLeft = (sparkleRadius - tickSize)/2
        val tickRight = tickLeft + tickSize
        val tickTop = (sparkleRadius - tickSize)/2
        val tickBottom = tickTop + tickSize

        val tickStart = Pair(tickLeft, tickTop + tickSize/2)
        val tickMiddle = Pair(tickLeft + tickSize/3, tickBottom)
        val tickEnd = Pair(tickRight, tickTop)

        drawLine(
                color = color,
                start = Offset(
                        x = tickStart.first.toPx(),
                        y = tickStart.second.toPx()
                ),
                end = Offset(
                        x = if(tailAnimation < 0.3f) {
                            (tickStart.first + (tickMiddle.first - tickStart.first) * tailAnimation * 3.33f).toPx()
                        } else {
                            tickMiddle.first.toPx()
                        },
                        y = if(tailAnimation < 0.3) {
                            (tickStart.second + (tickMiddle.second - tickStart.second) * tailAnimation * 3.33f).toPx()
                        } else {
                            tickMiddle.second.toPx()
                        }
                ),
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round
        )

        if(tailAnimation >= 0.3)
            drawLine(
                    color = color,
                    start = Offset(
                            x = tickMiddle.first.toPx(),
                            y = tickMiddle.second.toPx()
                    ),
                    end = Offset(
                            x = (tickMiddle.first + (tickEnd.first - tickMiddle.first) * (tailAnimation - 0.3f) * 1.43f).toPx(),
                            y = (tickMiddle.second + (tickEnd.second - tickMiddle.second) * (tailAnimation - 0.3f) * 1.43f).toPx(),
                    ),
                    strokeWidth = strokeWidth,
                    cap = StrokeCap.Round
            )

        if(tailAnimation >= 0.3)
            (1..10).forEach {
                val angle = 2 * Math.PI * it/10
                drawCircle(
                        color = color,
                        center = Offset(
                                width/2 + (tickSize/2 + ((sparkleRadius - tickSize)/2 * (tailAnimation - 0.3f) * 3.33f)).toPx() * sin(angle).toFloat(),
                                height/2 + (tickSize/2 + ((sparkleRadius - tickSize)/2 * (tailAnimation - 0.3f) * 3.33f)).toPx() * cos(angle).toFloat()
                        ),
                        radius = strokeWidth * (1 - (tailAnimation - 0.3f) * 3.33f)
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
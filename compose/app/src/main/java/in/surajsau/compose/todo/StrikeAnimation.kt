package `in`.surajsau.compose.todo

import androidx.compose.animation.animatedFloat
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// https://dribbble.com/shots/3959132-Todo-List-Swipe-To-Check

@Composable
fun StrikeAnimation(modifier: Modifier = Modifier) {
    ConstraintLayout(modifier = modifier) {
        var isStrikeThrough = remember { false }
        val (strike, text) = createRefs()

        val boxAnimation = animatedFloat(initVal = 0f)
        val tailAnimation = animatedFloat(initVal = 0f)

        Canvas(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .constrainAs(strike) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {

            // right side top half

            if (boxAnimation.value < 0.1f)
                drawLine(
                        Color.Black,
                        start = Offset(50.dp.toPx(),
                                calculateValue(
                                        progress = boxAnimation.value,
                                        initValue = 50.dp.toPx(),
                                        finalValue = 42.dp.toPx(),
                                        initProgress = 0f,
                                        finalProgress = 0.1f
                                )
                        ),
                        end = Offset(50.dp.toPx(), 42.dp.toPx()),
                        strokeWidth = 6f, cap = StrokeCap.Round)

            // top right corner
            if (boxAnimation.value < 0.15f)
                drawArc(
                        Color.Black,
                        startAngle = calculateValue(
                                progress = boxAnimation.value,
                                initProgress = 0.1f,
                                finalProgress = 0.15f,
                                initValue = 0f,
                                finalValue = -90f
                        ),
                        sweepAngle = calculateValue(
                                progress = boxAnimation.value,
                                initProgress = 0.1f,
                                finalProgress = 0.15f,
                                initValue = -90f,
                                finalValue = 0f
                        ),
                        useCenter = false,
                        size = Size(8.dp.toPx(), 8.dp.toPx()),
                        topLeft = Offset(42.dp.toPx(), 38.dp.toPx()),
                        style = Stroke(6f, cap = StrokeCap.Round)
                )

            // top side
            if (boxAnimation.value < 0.25f)
                drawLine(
                        Color.Black,
                        start = Offset(
                                calculateValue(
                                        progress = boxAnimation.value,
                                        initValue = 46.dp.toPx(),
                                        finalValue = 30.dp.toPx(),
                                        initProgress = 0.15f,
                                        finalProgress = 0.25f
                                ),
                                38.dp.toPx()
                        ),
                        end = Offset(30.dp.toPx(), 38.dp.toPx()),
                        strokeWidth = 6f, cap = StrokeCap.Round)

            // top left corner
            if (boxAnimation.value < 0.30f)
                drawArc(
                        Color.Black,
                        startAngle = calculateValue(
                                progress = boxAnimation.value,
                                initValue = -90f,
                                finalValue = -180f,
                                initProgress = 0.25f,
                                finalProgress = 0.30f
                        ),
                        sweepAngle = calculateValue(
                                progress = boxAnimation.value,
                                initValue = -90f,
                                finalValue = 0f,
                                initProgress = 0.25f,
                                finalProgress = 0.30f
                        ),
                        useCenter = false,
                        size = Size(8.dp.toPx(), 8.dp.toPx()),
                        topLeft = Offset(26.dp.toPx(), 38.dp.toPx()),
                        style = Stroke(6f, cap = StrokeCap.Round)
                )

            // left side
            if (boxAnimation.value < 0.4f)
                drawLine(
                        Color.Black,
                        start = Offset(26.dp.toPx(),
                                calculateValue(
                                        progress = boxAnimation.value,
                                        initValue = 42.dp.toPx(),
                                        finalValue = 58.dp.toPx(),
                                        initProgress = 0.3f,
                                        finalProgress = 0.4f
                                )
                        ),
                        end = Offset(26.dp.toPx(), 58.dp.toPx()),
                        strokeWidth = 6f, cap = StrokeCap.Round)

            // bottom left corner
            if (boxAnimation.value < 0.45f)
                drawArc(
                        Color.Black,
                        startAngle = calculateValue(
                                progress = boxAnimation.value,
                                initValue = -180f,
                                finalValue = -270f,
                                initProgress = 0.4f,
                                finalProgress = 0.45f
                        ),
                        sweepAngle = calculateValue(
                                progress = boxAnimation.value,
                                initValue = -90f,
                                finalValue = 0f,
                                initProgress = 0.4f,
                                finalProgress = 0.45f
                        ),
                        useCenter = false,
                        size = Size(8.dp.toPx(), 8.dp.toPx()),
                        topLeft = Offset(26.dp.toPx(), 54.dp.toPx()),
                        style = Stroke(6f, cap = StrokeCap.Round)
                )

            // bottom side
            if(boxAnimation.value < 0.55f)
                drawLine(
                        Color.Black,
                        start = Offset(
                                calculateValue(
                                        progress = boxAnimation.value,
                                        initValue = 30.dp.toPx(),
                                        finalValue = 46.dp.toPx(),
                                        initProgress = 0.45f,
                                        finalProgress = 0.55f
                                )
                                , 62.dp.toPx()),
                        end = Offset(46.dp.toPx(), 62.dp.toPx()),
                        strokeWidth = 6f, cap = StrokeCap.Round)

            // bottom right corner
            if(boxAnimation.value < 0.6f)
                drawArc(
                        Color.Black,
                        startAngle = calculateValue(
                                progress = boxAnimation.value,
                                initValue = -270f,
                                finalValue = -360f,
                                initProgress = 0.55f,
                                finalProgress = 0.6f
                        ),
                        sweepAngle = calculateValue(
                                progress = boxAnimation.value,
                                initValue = -90f,
                                finalValue = 0f,
                                initProgress = 0.55f,
                                finalProgress = 0.6f
                        ),
                        useCenter = false,
                        size = Size(8.dp.toPx(), 8.dp.toPx()),
                        topLeft = Offset(42.dp.toPx(), 54.dp.toPx()),
                        style = Stroke(6f, cap = StrokeCap.Round)
                )

            // right side bottom half
            if(boxAnimation.value < 0.7f)
                drawLine(
                        Color.Black,
                        start = Offset(50.dp.toPx(),
                                calculateValue(
                                        progress = boxAnimation.value,
                                        initValue = 58.dp.toPx(),
                                        finalValue = 50.dp.toPx(),
                                        initProgress = 0.6f,
                                        finalProgress = 0.7f
                                )
                        ),
                        end = Offset(50.dp.toPx(), 50.dp.toPx()),
                        strokeWidth = 6f, cap = StrokeCap.Round)

            // strike line
            drawLine(
                    Color.Black,
                    start = Offset(
                            calculateValue(
                                    progress = boxAnimation.value,
                                    initValue = 50.dp.toPx(),
                                    finalValue = 60.dp.toPx(),
                                    initProgress = 0.75f,
                                    finalProgress = 1f
                            )
                            , 50.dp.toPx()),
                    end = Offset(50.dp.toPx() + 300.dp.toPx() * tailAnimation.value, 50.dp.toPx()),
                    strokeWidth = 6f, cap = StrokeCap.Round)
        }
        
        Text(text = "Do an important task",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(text) {
                    start.linkTo(parent.start, margin = 60.dp)
                    top.linkTo(strike.top)
                    bottom.linkTo(strike.bottom)
                }.clickable(onClick = {
                    isStrikeThrough = !isStrikeThrough
                    boxAnimation.animateTo(
                            targetValue = if(isStrikeThrough) 1f else 0f,
                            anim = tween(durationMillis = 1000, easing = LinearEasing)
                    )

                    tailAnimation.animateTo(
                            targetValue = if(isStrikeThrough) 1f else 0f,
                            anim = tween(durationMillis = 1000, easing = LinearEasing)
                    )
                })
        )
    }
}

fun calculateValue(
    progress: Float,
    initValue: Float,
    finalValue: Float,
    initProgress: Float,
    finalProgress: Float
): Float {
    return when {
        progress < initProgress -> initValue
        else -> initValue + ((finalValue - initValue) * ((progress - initProgress)/(finalProgress - initProgress)))
    }
}

@Preview
@Composable
fun previewStrikeAnimation() {
    Box(modifier = Modifier.fillMaxSize()) {
        StrikeAnimation(modifier = Modifier.fillMaxSize())
    }
}
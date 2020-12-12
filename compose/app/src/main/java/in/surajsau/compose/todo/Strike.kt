package `in`.surajsau.compose.todo

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Strike(
        color: Color,
        modifier: Modifier = Modifier,
        curveRadius: Dp = 4.dp,
        sideLength: Dp = 24.dp,
        padding: PaddingValues = PaddingValues(start = 16.dp),
        strokeWidth: Float = 6f,
        headAnimation: Float,
        tailAnimation: Float,
) {
    Canvas(modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {

        val height = size.height

        val left = padding.start.toPx()
        val right = left + sideLength.toPx()
        val top = height/2 - sideLength.toPx()/2
        val bottom = height/2 + sideLength.toPx()/2

        // right side top half

        if (headAnimation < 0.1f)
            drawLine(
                    color,
                    start = Offset(
                            right,
                            calculateValue(
                                    progress = headAnimation,
                                    initValue = height/2,
                                    finalValue = top + curveRadius.toPx(),
                                    initProgress = 0f,
                                    finalProgress = 0.1f
                            )
                    ),
                    end = Offset(
                            right,
                            top + curveRadius.toPx()
                    ),
                    strokeWidth = strokeWidth, cap = StrokeCap.Round)

        // top right corner
        if (headAnimation < 0.15f)
            drawArc(
                    color,
                    startAngle = calculateValue(
                            progress = headAnimation,
                            initProgress = 0.1f,
                            finalProgress = 0.15f,
                            initValue = 0f,
                            finalValue = -90f
                    ),
                    sweepAngle = calculateValue(
                            progress = headAnimation,
                            initProgress = 0.1f,
                            finalProgress = 0.15f,
                            initValue = -90f,
                            finalValue = 0f
                    ),
                    useCenter = false,
                    size = Size(width = (curveRadius * 2).toPx(), height = (curveRadius * 2).toPx()),
                    topLeft = Offset(
                            right - (curveRadius * 2).toPx(),
                            top
                    ),
                    style = Stroke(strokeWidth, cap = StrokeCap.Round)
            )

        // top side
        if (headAnimation < 0.25f)
            drawLine(
                    color,
                    start = Offset(
                            calculateValue(
                                    progress = headAnimation,
                                    initValue = right - curveRadius.toPx(),
                                    finalValue = left + curveRadius.toPx(),
                                    initProgress = 0.15f,
                                    finalProgress = 0.25f
                            ),
                            top
                    ),
                    end = Offset(left + curveRadius.toPx(), top),
                    strokeWidth = strokeWidth, cap = StrokeCap.Round)

        // top left corner
        if (headAnimation < 0.30f)
            drawArc(
                    color,
                    startAngle = calculateValue(
                            progress = headAnimation,
                            initValue = -90f,
                            finalValue = -180f,
                            initProgress = 0.25f,
                            finalProgress = 0.30f
                    ),
                    sweepAngle = calculateValue(
                            progress = headAnimation,
                            initValue = -90f,
                            finalValue = 0f,
                            initProgress = 0.25f,
                            finalProgress = 0.30f
                    ),
                    useCenter = false,
                    size = Size((curveRadius * 2).toPx(), (curveRadius * 2).toPx()),
                    topLeft = Offset(left, top),
                    style = Stroke(strokeWidth, cap = StrokeCap.Round)
            )

        // left side
        if (headAnimation < 0.4f)
            drawLine(
                    color,
                    start = Offset(
                            left,
                            calculateValue(
                                    progress = headAnimation,
                                    initValue = top + curveRadius.toPx(),
                                    finalValue = bottom - curveRadius.toPx(),
                                    initProgress = 0.3f,
                                    finalProgress = 0.4f
                            )
                    ),
                    end = Offset(left, bottom - curveRadius.toPx()),
                    strokeWidth = strokeWidth, cap = StrokeCap.Round)

        // bottom left corner
        if (headAnimation < 0.45f)
            drawArc(
                    color,
                    startAngle = calculateValue(
                            progress = headAnimation,
                            initValue = -180f,
                            finalValue = -270f,
                            initProgress = 0.4f,
                            finalProgress = 0.45f
                    ),
                    sweepAngle = calculateValue(
                            progress = headAnimation,
                            initValue = -90f,
                            finalValue = 0f,
                            initProgress = 0.4f,
                            finalProgress = 0.45f
                    ),
                    useCenter = false,
                    size = Size((curveRadius * 2).toPx(), (curveRadius * 2).toPx()),
                    topLeft = Offset(left, bottom - (curveRadius * 2).toPx()),
                    style = Stroke(strokeWidth, cap = StrokeCap.Round)
            )

        // bottom side
        if(headAnimation < 0.55f)
            drawLine(
                    color,
                    start = Offset(
                            calculateValue(
                                    progress = headAnimation,
                                    initValue = left + curveRadius.toPx(),
                                    finalValue = right - curveRadius.toPx(),
                                    initProgress = 0.45f,
                                    finalProgress = 0.55f
                            )
                            , bottom),
                    end = Offset(right - curveRadius.toPx(), bottom),
                    strokeWidth = strokeWidth, cap = StrokeCap.Round)

        // bottom right corner
        if(headAnimation < 0.6f)
            drawArc(
                    color,
                    startAngle = calculateValue(
                            progress = headAnimation,
                            initValue = -270f,
                            finalValue = -360f,
                            initProgress = 0.55f,
                            finalProgress = 0.6f
                    ),
                    sweepAngle = calculateValue(
                            progress = headAnimation,
                            initValue = -90f,
                            finalValue = 0f,
                            initProgress = 0.55f,
                            finalProgress = 0.6f
                    ),
                    useCenter = false,
                    size = Size((curveRadius * 2).toPx(), (curveRadius * 2).toPx()),
                    topLeft = Offset(right - (curveRadius * 2).toPx(), bottom - (curveRadius * 2).toPx()),
                    style = Stroke(strokeWidth, cap = StrokeCap.Round)
            )

        // right side bottom half
        if(headAnimation < 0.7f)
            drawLine(
                    color,
                    start = Offset(
                            right,
                            calculateValue(
                                    progress = headAnimation,
                                    initValue = bottom - curveRadius.toPx(),
                                    finalValue = height/2,
                                    initProgress = 0.6f,
                                    finalProgress = 0.7f
                            )
                    ),
                    end = Offset(right, height/2),
                    strokeWidth = strokeWidth, cap = StrokeCap.Round)

        // strike line
        drawLine(
                color,
                start = Offset(
                        calculateValue(
                                progress = headAnimation,
                                initValue = right,
                                finalValue = right + 10.dp.toPx(),
                                initProgress = 0.75f,
                                finalProgress = 1f
                        )
                        , height/2),
                end = Offset(right + 200.dp.toPx() * tailAnimation, height/2),
                strokeWidth = strokeWidth, cap = StrokeCap.Round)
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
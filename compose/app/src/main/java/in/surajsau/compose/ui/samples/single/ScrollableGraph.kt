package `in`.surajsau.compose.ui.samples.single

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

fun rand(start: Float, end: Float): Float {
    return (Math.random() * (end - start + 1)).toFloat() + start
}

@Composable
fun ScrollableGraphScreen() {

    var animate by remember { mutableStateOf(false) }

    val intAnimate by animateIntAsState(
        targetValue = if (animate) 200 else 0,
        animationSpec =
    )

    val 

    Column(modifier = Modifier.fillMaxWidth()) {
        Graph(
            current = intAnimate,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            values = (0..200).map { rand(50f, 100f) }
        )

        Button(onClick = { animate = true }) {
            Text(text = "Animate")
        }
    }
}

@Composable
fun Graph(
    current: Int = 0,
    modifier: Modifier = Modifier,
    values: List<Float>,
) {

    val density = LocalDensity.current

    Canvas(modifier = modifier.background(Color.LightGray)) {
        val width = size.width
        val height = size.height

        val dx = width/values.size
        val dy = height/values.size

        val path = Path()

        values.forEachIndexed { index, value ->
            val x = dx * index
            val y = height - (dy * value)
            if (index == 0)
                path.moveTo(x, y)
            else
                path.lineTo(x, y)
        }

        values[current].let {
            val x = dx * current
            val y = height - (dy * it)

            drawCircle(Color.Red, center = Offset(x, y), radius = 10f)
        }

        drawPath(
            path = path,
            color = Color.Black,
            style = Stroke(
                width = with(density) { 3.dp.toPx() },
                cap = StrokeCap.Round,
                join = StrokeJoin.Miter,
            )
        )

        path.reset()
    }
}

@Preview
@Composable
fun previewScrollableGraphScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        ScrollableGraphScreen()
    }
}
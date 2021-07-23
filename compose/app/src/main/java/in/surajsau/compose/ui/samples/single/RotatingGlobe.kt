package `in`.surajsau.compose.ui.samples.single

import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

private const val TWO_PI = 2 * Math.PI.toFloat()
private const val PI = Math.PI.toFloat()

@Composable
fun RotatingGlobeScreen() {

    Column(modifier = Modifier.fillMaxSize()) {

        RotatingGlobe(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .padding(16.dp),
            numberOfDots = 1000,
            inclinationAngle = PI/3
        )
    }
}

@Composable
fun RotatingGlobe(
    inclinationAngle: Float = 0f,
    globeRadiusSize: Float = 0.7f,
    numberOfDots: Int = 1000,
    dotSize: Dp = 5.dp,
    dotColor: Color = if (isSystemInDarkTheme()) Color.White else Color.Black,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition()

    val animatedProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 20000, easing = LinearEasing),
        )
    )

    val dotInfos = remember {
        (0 until numberOfDots).map {
                val azimuthAngle = acos((Math.random().toFloat() * 2) - 1)
                val polarAngle = Math.random().toFloat() * TWO_PI
                DotInfo(azimuthAngle, polarAngle)
        }
    }

    Canvas(modifier = modifier) {

        val minDimension = size.minDimension

        val center = minDimension/2

        val rotationY = animatedProgress * TWO_PI
        dotInfos.forEach {
            val globeRadius = minDimension * globeRadiusSize

            val x = globeRadius * sin(it.azimuthAngle) * cos(it.polarAngle)
            val y = globeRadius * sin(it.azimuthAngle) * sin(it.polarAngle)
            val z = globeRadius * cos(it.azimuthAngle) - globeRadius

            val rotationX =
                x * cos(rotationY + inclinationAngle) + (z + globeRadius) * sin(rotationY + inclinationAngle)
            val rotationZ =
                -x * sin(rotationY + inclinationAngle) + cos(rotationY + inclinationAngle) * (z + globeRadius) - globeRadius

            val scale = minDimension / (minDimension - rotationZ)

            val projectedX = (rotationX * scale)
            val projectedY = (y * scale)

            val rotatedX =
                projectedX * cos(inclinationAngle) - projectedY * sin(inclinationAngle)
            val rotatedY =
                projectedX * sin(inclinationAngle) + projectedY * cos(inclinationAngle)

            drawCircle(
                color = dotColor,
                radius = dotSize.value * scale,
                center = Offset(rotatedX + center, rotatedY + center),
            )
        }
    }
}

private data class DotInfo(val azimuthAngle: Float, val polarAngle: Float)
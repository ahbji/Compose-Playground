package `in`.surajsau.compose.ui.samples.single

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import kotlin.math.cos
import kotlin.math.sin

private const val TWO_PI = 2 * Math.PI.toFloat()
private const val PI = Math.PI.toFloat()

private val WorldMap = listOf(
    /* 90   "|...|...|...|...|...|...|...|...|...*...|...|...|...|...|...|...|...|...", */
    /* 90 */"........................................................................",
    /* 85 */"................x..xxx..xxxxxxxxx.......................................",
    /* 80 */"................x.xxxx..xxxxxxxxx.......................................",
    /* 75 */"............xx.....x......xxxxx.......................xxxxxxx...........",
    /* 70 */"....xxxx....xxxxxx..x.....xxxxx.........xxx.........xxxxxxxxxxxxxxxx....",
    /* 65 */"..xxxxxxxxxxxxxxxxx..xx....xx.....x....xx.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
    /* 60 */"..xxxx..xxxxxxxxxx..xxx.....x.....x....x..xxxxxxxxxxxxxxxxxxxxxxx...xxx..",
    /* 55 */".........xxxxxxxxx..xxxx.........xx.xxxxxxxxxxxxxxxxxxxxxxxxxxxxx...x...",
    /* 50 */"..........xxxxxxxxxxxxxx.........xx.xxxxxxxxxxxxxxxxxxxxxxxxxxxxx...x...",
    /* 45 */"...........xxxxxxxxxxx..............xx.x..xxxx.xxxxxxxxxxxxxxxxx...x....",
    /* 40 */"...........xxxxxxxxxxx.............xx...x.xxxx.xxxxxxxxxxxxxxxx....x....",
    /* 35 */".............xxxxxx.x..............xxxxxxxx.xxxxxxxxxxxxxxxxx.x...x.....",
    /* 30 */".............xxxxxx.x..............xxxxxxxx.xxxxxxxxxxxxxxxxx.....x.....",
    /* 25 */"...............xx................xxxxxxxxxxx.xxxx.xxxxxxxxxxx...........",
    /* 20 */"...............xx................xxxxxxxxxxx.xxxx..xxxx.xxx.............",
    /* 15 */".................xx..............xxxxxxxxxxxx.x....xx....x..............",
    /* 10 */"....................x.xxx.........xxxxxxxxxxxxx.....x....x..............",
    /*  5 */".....................xxxxx...........xxxxxxxx........x...xx.x...........",
    /*  0 */"....................xxxxxxx...........xxxxxxx............xx.x...........",
    /*-10 */"...................xxxxxxxxxxx.........xxxxx.....................x......",
    /*-15 */"....................xxxxxxxxxx.........xxxxx..................x.xx......",
    /*-20 */"......................xxxxxxx..........xxxx...x.............xxxxxx......",
    /*-25 */"......................xxxxxxx..........xxxx...x.............xxxxxx......",
    /*-30 */"......................xxxx..............xx.................xxxxxxxx.....",
    /*-35 */"......................xxxx..............xx..................x...xxx.....",
    /*-40 */".....................xxx........................................xx.....x",
    /*-45 */".....................xxx.........................................x.....x",
    /*-50 */".....................xx...............................................x.",
    /*-55 */".....................xx...............................................x.",
    /*-60 */".....................x..................................................",
    /*-65 */"......................x.................................................",
    /*-70 */"........................................................................",
    /*-75 */"........................................................................",
    /*-80 */"........................................................................",
    /*-85 */"........................................................................",
    /*-90 */"........................................................................",
)

@Composable
fun OlympicsGlobeScreen() {

    Column(modifier = Modifier.fillMaxSize()
        .background(color = Color.Black)) {

        OlympicsGlobe(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .padding(16.dp),
            dotColor = Color.White,
        )
    }
}

@Composable
fun OlympicsGlobe(
    inclinationAngle: Float = 0f,
    globeRadiusSize: Float = 0.7f,
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
        WorldMap.reversed().mapIndexed { i, str ->
            str.reversed().toCharArray().mapIndexed { j, char ->
                if (char == 'x')
                    DotCoordinate(i * PI/36, j * PI/36)
                else
                    InvalidCoordinate
            }
        }.flatten()
    }

    Canvas(modifier = modifier) {

        val minDimension = size.minDimension

        val center = minDimension/2

        val rotationZ = animatedProgress * TWO_PI
        dotInfos.forEach {
            if (it is DotCoordinate) {
                val globeRadius = minDimension * globeRadiusSize

                val x = globeRadius * sin(it.azimuthAngle) * cos(it.polarAngle)
                val y = globeRadius * sin(it.azimuthAngle) * sin(it.polarAngle)
                val z = globeRadius * cos(it.azimuthAngle)

                val rotationX =
                    x * cos(rotationZ + inclinationAngle) + (y) * sin(rotationZ + inclinationAngle)
                val rotationY =
                    -x * sin(rotationZ + inclinationAngle) + cos(rotationZ + inclinationAngle) * (y) - globeRadius

                val scale = minDimension / (minDimension - rotationY)

                val projectedX = (rotationX * scale)
                val projectedY = (z * scale)

                val inclinedX =
                    projectedX * cos(inclinationAngle) - projectedY * sin(inclinationAngle)
                val inclinedY =
                    projectedX * sin(inclinationAngle) + projectedY * cos(inclinationAngle)

                drawCircle(
                    color = if (scale > 0.65) dotColor else Color.Blue,
                    radius = dotSize.value * scale,
                    center = Offset(inclinedX + center, inclinedY + center),
                )
            }
        }
    }
}

sealed class Coordinate

private data class DotCoordinate(val azimuthAngle: Float, val polarAngle: Float): Coordinate()
private object InvalidCoordinate: Coordinate()
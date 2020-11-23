package `in`.surajsau.tenji.ticker

import `in`.surajsau.tenji.androidx.Progress
import `in`.surajsau.tenji.androidx.ValueAnimator
import `in`.surajsau.tenji.androidx.animationProgress
import android.util.Log
import androidx.compose.animation.core.FloatPropKey
import androidx.compose.animation.core.IntPropKey
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.TransitionState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween
import androidx.compose.animation.transition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import kotlin.time.DurationUnit

val currentTickerValue = FloatPropKey()

@Composable
fun Ticker(
        from: Int,
        to: Int
) {
    _Ticker(
            state = transition(
                    definition = transitionDefinition {
                        state(0) { this[currentTickerValue] = from.toFloat() }
                        state(1) { this[currentTickerValue] = to.toFloat() }

                        transition(0 to 1) {
                            currentTickerValue using tween(
                                    durationMillis = (to - from) * 3_00,
                                    easing = LinearEasing
                            )
                        }
                    },
                    initState = 0,
                    toState = 1
            )
    )
}

@Composable
private fun _Ticker(
        state: TransitionState
) {
    val toString = "${state[currentTickerValue].toInt()}"
    val delta = state[currentTickerValue] - state[currentTickerValue].toInt()
    val numberOfDigits = toString.length
    Box(modifier = Modifier.size(
            width = (numberOfDigits * 30).dp,
            height = 50.dp
        )
    ) {
        toString.toCharArray().forEachIndexed { index, char ->
            val yOffset = when {
                index == (toString.length - 1) -> (delta * 50).dp
                toString[index + 1] == '9' -> (delta * 50).dp
                else -> 0.dp
            }

            Text(
                    text = char.toString(),
                    fontSize = 40.sp,
                    color = Color.Red,
                    modifier = Modifier.offset(
                            x = (index * 30).dp,
                            y = 0.dp - yOffset
                    )
            )
            Text(
                    text = (if(char == '9') '0' else (char + 1)).toString(),
                    fontSize = 40.sp,
                    color = Color.Red,
                    modifier = Modifier.offset(
                            x = (index * 30).dp,
                            y = 50.dp - yOffset
                    )
            )
        }
    }
}

@Preview
@Composable
fun PreviewTicker() {
    Ticker(
            from = 0,
            to = 5
    )
}
package `in`.surajsau.tenji.ticker

import androidx.compose.animation.core.FloatPropKey
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.TransitionState
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween
import androidx.compose.animation.transition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import kotlin.math.abs

val currentTickerValue = FloatPropKey()

@Composable
fun Ticker(
        from: Int,
        to: Int,
        modifier: Modifier = Modifier,
) {
    TickerText(
            state = transition(
                    definition = transitionDefinition {
                        state(0) { this[currentTickerValue] = from.toFloat() }
                        state(1) { this[currentTickerValue] = to.toFloat() }

                        transition(0 to 1) {
                            currentTickerValue using tween(
                                    durationMillis = abs(to - from) * 3_00,
                                    easing = LinearEasing
                            )
                        }
                    },
                    initState = 0,
                    toState = 1,
            ),
            modifier = modifier
    )
}

@Composable
fun TickerText(
        state: TransitionState,
        modifier: Modifier = Modifier
) {
    val toString = "${state[currentTickerValue].toInt()}"
    val delta = state[currentTickerValue] - state[currentTickerValue].toInt()
    val numberOfDigits = toString.length
    Box(modifier = modifier.size(
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
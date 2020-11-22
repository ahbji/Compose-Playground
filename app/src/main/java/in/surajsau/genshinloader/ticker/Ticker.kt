package `in`.surajsau.genshinloader.ticker

import `in`.surajsau.genshinloader.androidx.Progress
import `in`.surajsau.genshinloader.androidx.ValueAnimator
import `in`.surajsau.genshinloader.androidx.animationProgress
import androidx.compose.animation.core.TransitionState
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

@Composable
fun Ticker(
        state: TransitionState,
        value: Int
) {
    val toString = value.toString()
    val numberOfDigits = toString.length
    Box(modifier = Modifier.size(
            width = (numberOfDigits * 30).dp,
            height = 50.dp
        )
    ) {
        toString.toCharArray().forEachIndexed { index, char ->
            val yOffset = when {
                index == (toString.length - 1) -> (state[animationProgress] * 50).dp
                toString[index + 1] == '9' -> (state[animationProgress] * 50).dp
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
                    text = (char + 1).toString(),
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
    val transition = transition(
            definition = ValueAnimator.definition(),
            toState = Progress.END,
            initState = Progress.START
    )
    Ticker(state = transition, value = 12)
}
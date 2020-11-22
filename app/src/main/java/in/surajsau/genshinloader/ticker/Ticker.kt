package `in`.surajsau.genshinloader.ticker

import androidx.compose.animation.core.FloatPropKey
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween
import androidx.compose.animation.transition
import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.Preview

@Composable
fun Ticker() {
    val transition = transition(
        definition = valueAnimatorDefinition,
        initState = Progress.START,
        toState = Progress.END
    )
    
    TickerColumn(state = transition, digit = 0)
}

@Preview
@Composable
fun PreviewTicker() {
    Ticker()
}

val animationProgress = FloatPropKey()

val valueAnimatorDefinition = transitionDefinition<Progress> {
    state(Progress.START) { this[animationProgress] = 0f }
    state(Progress.END) { this[animationProgress] = 1f }

    transition(
        fromState = Progress.START,
        toState = Progress.END
    ) {
        animationProgress using tween(
            durationMillis = 1_000
        )
    }
}

enum class Progress {
    START, END
}
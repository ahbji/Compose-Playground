package `in`.surajsau.compose.screens

import androidx.compose.animation.core.FloatPropKey
import androidx.compose.animation.core.TransitionState
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween
import androidx.compose.animation.transition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.gesture.pressIndicatorGestureFilter
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradient
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

enum class ButtonState {
    IDLE, PRESSED
}

val NeomorphLightColor = Color(255, 255, 255)
val NeomorphDarkColor = Color(200, 210, 220)
val NeomorphColor = Color(230, 232, 234)

val buttonStateProgress = FloatPropKey()

@Composable
fun NeomorphButton(
    modifier: Modifier = Modifier
) {
    val buttonState = remember { mutableStateOf(ButtonState.IDLE) }
    val fromState = if(buttonState.value == ButtonState.IDLE) {
        ButtonState.PRESSED
    } else {
        ButtonState.IDLE
    }

    val state = transition(
            definition = transitionDefinition {
                state(ButtonState.IDLE) { this[buttonStateProgress] = 0f }
                state(ButtonState.PRESSED) { this[buttonStateProgress] = 1f }

                transition(
                    ButtonState.IDLE to ButtonState.PRESSED,
                        ButtonState.PRESSED to ButtonState.IDLE
                ) {
                    buttonStateProgress using tween()
                }
            },
            initState = buttonState.value,
            toState = fromState
    )


    CircleNeomorph(state = state, modifier = modifier
            .pressIndicatorGestureFilter(
                    onStart = { buttonState.value = ButtonState.PRESSED },
                    onStop = { buttonState.value = ButtonState.IDLE }
            )
    )
}

@Composable
fun CircleNeomorph(
        state: TransitionState,
        modifier: Modifier = Modifier,
) {
    val bias = (3 - 2f * state[buttonStateProgress])

    Box(modifier = modifier.size(size = 200.dp)) {
        Box(modifier = Modifier.size(120.dp)
                .clip(shape = CircleShape)
                .background(
                        brush = RadialGradient(
                                colors = listOf(NeomorphLightColor, NeomorphLightColor, Color.Transparent),
                                radius = 120.dp.value,
                                centerX = 120.dp.value,
                                centerY = 120.dp.value
                        )
                )
                .align(alignment = BiasAlignment(bias * -0.1f, bias * -0.1f))
        )

        Box(modifier = Modifier.size(120.dp)
                .clip(shape = CircleShape)
                .background(
                        brush = RadialGradient(
                                colors = listOf(NeomorphDarkColor, NeomorphDarkColor, Color.Transparent),
                                radius = 120.dp.value,
                                centerX = 120.dp.value,
                                centerY = 120.dp.value
                        )
                )
                .align(alignment = BiasAlignment(bias * 0.1f, bias * 0.1f))
        )

        Box(modifier = Modifier.size(size = 100.dp)
                .clip(shape = CircleShape)
                .align(alignment = Alignment.Center)
                .background(color = NeomorphColor))
    }
}

@Preview
@Composable
fun PreviewNeomorphButton() {

    Box(modifier = Modifier.background(color = NeomorphColor)
            .fillMaxSize()) {
        NeomorphButton(
                modifier = Modifier.align(alignment = Alignment.Center)
        )
    }

}
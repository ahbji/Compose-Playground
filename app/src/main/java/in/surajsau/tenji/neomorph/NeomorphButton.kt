package `in`.surajsau.tenji.neomorph

import `in`.surajsau.tenji.androidx.Shape
import androidx.compose.animation.core.FloatPropKey
import androidx.compose.animation.core.TransitionState
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween
import androidx.compose.animation.transition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonConstants
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview

enum class ButtonState {
    IDLE, PRESSED
}

val NeomorphLightColor = Color(254, 254, 254)
val NeomorphDarkColor = Color(209, 217, 229)
val NeomorphColor = Color(236, 238, 240)

val buttonStateProgress = FloatPropKey()

@Composable
fun NeomorphButton(
        state: TransitionState,
        buttonState: MutableState<ButtonState>,
        onClick: (() -> Unit)? = null
) {

    Box(modifier = Modifier.size(size = 100.dp)) {
        Shape(
                shape = CircleShape,
                size = Size(
                        width = 60f,
                        height = 60f
                ),
                backgroundColor = NeomorphDarkColor,
                modifier = Modifier.offset(
                        x = 25.dp - (20 * state[buttonStateProgress]).dp,
                        y = 25.dp - (20 * state[buttonStateProgress]).dp
                )
        )

        Shape(
                shape = CircleShape,
                size = Size(
                        width = 60f,
                        height = 60f
                ),
                backgroundColor = NeomorphLightColor,
                modifier = Modifier.offset(
                        x = 25.dp + (20 * state[buttonStateProgress]).dp,
                        y = 25.dp + (20 * state[buttonStateProgress]).dp
                )
        )
        Button(
                onClick = {
                    buttonState.value = if (buttonState.value == ButtonState.PRESSED) {
                        ButtonState.IDLE
                    } else {
                        ButtonState.PRESSED
                    }
                    onClick?.invoke()
                },
                colors = ButtonConstants.defaultButtonColors(
                        backgroundColor = NeomorphColor
                ),
                shape = RoundedCornerShape (size = 30.dp),
                modifier = Modifier.size(size = 60.dp)
                        .align(alignment = Alignment.Center)
                        .drawShadow(elevation = 0.dp)
        ) {}
    }
}

@Preview
@Composable
fun PreviewNeomorphButton() {

    val buttonState = remember { mutableStateOf(ButtonState.IDLE) }
    val state = transition(
            definition = transitionDefinition {
                state(ButtonState.IDLE) { this[buttonStateProgress] = 0f }
                state(ButtonState.PRESSED) { this[buttonStateProgress] = 1f }

                transition(ButtonState.IDLE to ButtonState.PRESSED) {
                    buttonStateProgress using tween()
                }

                transition(ButtonState.PRESSED to ButtonState.IDLE) {
                    buttonStateProgress using tween()
                }
            },
            initState = buttonState.value,
            toState = ButtonState.PRESSED
    )
    NeomorphButton(state = state, buttonState = buttonState)
}
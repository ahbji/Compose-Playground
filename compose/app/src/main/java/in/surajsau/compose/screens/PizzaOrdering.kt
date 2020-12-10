package `in`.surajsau.compose.screens

import `in`.surajsau.compose.androidx.AlignmentPropKey
import `in`.surajsau.compose.androidx.AlignmentVectorConverter
import androidx.compose.animation.DpPropKey
import androidx.compose.animation.animate
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.TransitionState
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween
import androidx.compose.animation.transition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.onActive
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticAmbientOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.WithConstraints
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val buttonWidth = DpPropKey("button_width")
val buttonHeight = DpPropKey("button_height")
val buttonYOffset = DpPropKey("yoffset")
val buttonMarginStart = DpPropKey("button_margin_start")

@Composable
fun PizzaOrdering(modifier: Modifier = Modifier) {

    WithConstraints(modifier) {
        val w = this.maxWidth
        val h = this.maxHeight

        Box(modifier = modifier) {
            val buttonState = remember { mutableStateOf(0) }
            val toState = (buttonState.value + 1) % 2

            val state = transition(definition = transitionDefinition {
                state(0) {
                    this[buttonWidth] = 48.dp
                    this[buttonYOffset] = -(h/2)
                    this[buttonHeight] = 96.dp
                    this[buttonMarginStart] = 16.dp
                }
                state(1) {
                    this[buttonWidth] = w
                    this[buttonYOffset] = 30.dp
                    this[buttonHeight] = 120.dp
                    this[buttonMarginStart] = 0.dp
                }
                transition(0 to 1, 1 to 0) {
                    buttonWidth using tween(durationMillis = 200, easing = FastOutLinearInEasing)
                    buttonHeight using tween(durationMillis = 200, easing = FastOutLinearInEasing)
                    buttonYOffset using tween(durationMillis = 200, easing = FastOutLinearInEasing)
                    buttonMarginStart using tween(durationMillis = 200, easing = FastOutLinearInEasing)
                }
            }, initState = buttonState.value, toState = toState)

            AddButton(
                    state = state,
                    onClick = { buttonState.value = (buttonState.value + 1) % 2 },
                    modifier = Modifier.align(alignment = Alignment.BottomStart)
            )

        }
    }
}

@Composable
fun AddButton(
        state: TransitionState,
        onClick: () -> Unit,
        modifier: Modifier = Modifier
) {
    Button(
            onClick = onClick,
            shape = RoundedCornerShape(size = 24.dp),
            modifier = modifier
                    .padding(start = state[buttonMarginStart])
                    .size(width = state[buttonWidth], height = 96.dp)
                    .offset(y = state[buttonYOffset])
    ) {}
}

@Composable
fun AddButton(
        modifier: Modifier = Modifier,
        buttonState: TransitionState,
        onClick: () -> Unit
) {

    Button(
            onClick = onClick,
            shape = RoundedCornerShape(size = 24.dp),
            modifier = modifier
                    .size(width = buttonState[buttonWidth], height = 96.dp)
    ) {}
}

@Preview
@Composable
fun previewPizzaOrdering() {
    PizzaOrdering(modifier = Modifier.fillMaxSize())
}
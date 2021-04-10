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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.gesture.pressIndicatorGestureFilter
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.WithConstraints
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.AmbientDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

enum class ButtonState {
    IDLE, PRESSED
}

val NeomorphLightColor = Color(255, 255, 255)
val NeomorphDarkColor = Color(200, 210, 220)
val NeomorphColor = Color(230, 232, 234)

val buttonStateProgress = FloatPropKey("neomorph_button_state")

@Composable
fun NeomorphButton(
    shape: Shape,
    modifier: Modifier = Modifier,
    enableAnimation: Boolean = true,
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


    Neomorph(state = state, modifier = modifier
            .pressIndicatorGestureFilter(
                    onStart = { if(enableAnimation) buttonState.value = ButtonState.PRESSED },
                    onStop = { if(enableAnimation) buttonState.value = ButtonState.IDLE }
            )
    )
}

@Composable
fun Neomorph(
    shape: Shape,
    state: TransitionState,
    modifier: Modifier = Modifier,
) {
    val bias = (1 + 2f * state[buttonStateProgress]) * 0.4f

    WithConstraints(modifier = modifier) {
        val width = this.maxWidth
        val height = this.maxHeight
        val centerWidth = with(AmbientDensity.current) { (width * 5f/7).toPx() }
        val centerHeight = with(AmbientDensity.current) { (height * 5f/7).toPx() }
        val shadowWidth = with(AmbientDensity.current) { (width * 6f/7).toPx() }
        val shadowHeight = with(AmbientDensity.current) { (height * 6f/7).toPx() }

        Shadow

        Box(modifier = modifier) {
            Box(modifier = Modifier.size(
                        width = with(AmbientDensity.current) { shadowWidth.toDp() },
                        height = with(AmbientDensity.current) { shadowHeight.toDp() }
                    )
                    .background(
                            brush = Brush.radialGradient(
                                    colors = listOf(NeomorphLightColor, NeomorphLightColor, Color.Transparent),
                                    radius = shadowSize/2,
                                    center = Offset(
                                        x = shadowSize/2 ,
                                        y = shadowSize/2,
                                    )
                            )
                    )
                    .align(alignment = BiasAlignment(-bias, -bias))
            )

            Box(modifier = Modifier.size(
                        width = with(AmbientDensity.current) { shadowWidth.toDp() },
                        height = with(AmbientDensity.current) { shadowHeight.toDp() }
                    )
                    .background(
                            brush = Brush.radialGradient(
                                    colors = listOf(NeomorphDarkColor, NeomorphDarkColor, Color.Transparent),
                                    radius = shadowSize/2,
                                    center = Offset(
                                        x = shadowSize/2 ,
                                        y = shadowSize/2,
                                    )
                            )
                    )
                    .align(alignment = BiasAlignment(bias, bias))
            )

            Box(modifier = Modifier.size(
                        width = with(AmbientDensity.current) { centerWidth.toDp() },
                        height = with(AmbientDensity.current) { centerHeight.toDp() }
                    )
                    .align(alignment = Alignment.Center)
                    .background(color = NeomorphColor))
        }
    }
}

@Preview
@Composable
fun PreviewNeomorphButton() {

    Box(modifier = Modifier.background(color = NeomorphColor)
            .fillMaxSize()) {
        NeomorphButton(
                modifier = Modifier.align(alignment = Alignment.Center)
                        .size(size = 400.dp)
        )
    }

}
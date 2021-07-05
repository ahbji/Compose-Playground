package `in`.surajsau.compose.ui.samples.neomorph

import android.util.Log
import android.view.MotionEvent
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradient
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

enum class ButtonState {
    IDLE, PRESSED
}

val NeomorphLightColor = Color(255, 255, 255)
val NeomorphDarkColor = Color(200, 210, 220)
val NeomorphColor = Color(230, 232, 234)



@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NeomorphButton(
    modifier: Modifier = Modifier
) {
    val buttonState = remember { mutableStateOf(ButtonState.IDLE) }
//    val fromState = if(buttonState.value == ButtonState.IDLE) {
//        ButtonState.PRESSED
//    } else {
//        ButtonState.IDLE
//    }

    val transition = updateTransition(buttonState, label = "Neomorph_button_state")

    val buttonStateProgress = transition.animateFloat(
        label = "neomorph_button_state_progress",
        transitionSpec = { tween() }
    ) {
        when(it.value) {
            ButtonState.IDLE -> 0f
            ButtonState.PRESSED -> 1f
        }
    }


    CircleNeomorph(
        buttonStateProgress = buttonStateProgress.value,
        modifier = modifier
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> buttonState.value = ButtonState.PRESSED
                    MotionEvent.ACTION_UP -> buttonState.value = ButtonState.IDLE
                }
                return@pointerInteropFilter true
            }
    )
}

@Composable
fun CircleNeomorph(
        buttonStateProgress: Float,
        modifier: Modifier = Modifier,
) {
    val bias = (3 - 2f * buttonStateProgress) * 0.4f

    BoxWithConstraints(modifier = modifier) {
        val w = this.maxWidth
        val centerSize = (w * 5f/7)
        val shadowSize = (w * 6f/7)

        Box(modifier = modifier) {
            Box(modifier = Modifier
                .size(size = shadowSize)
                .clip(shape = CircleShape)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(NeomorphLightColor, NeomorphLightColor, Color.Transparent),
                        center = Offset(shadowSize.value, shadowSize.value),
                        radius = shadowSize.value,
                    )
                )
                .align(alignment = BiasAlignment(-bias, -bias))
            )

            Box(modifier = Modifier
                .size(size = shadowSize)
                .clip(shape = CircleShape)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(NeomorphDarkColor, NeomorphDarkColor, Color.Transparent),
                        center = Offset(shadowSize.value, shadowSize.value),
                        radius = shadowSize.value,
                    )
                )
                .align(alignment = BiasAlignment(bias, bias))
            )

            Box(modifier = Modifier.size(size = centerSize)
                    .clip(shape = CircleShape)
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
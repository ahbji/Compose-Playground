package `in`.surajsau.tenji.googlefonts

import `in`.surajsau.tenji.androidx.Circle
import androidx.compose.animation.ColorPropKey
import androidx.compose.animation.DpPropKey
import androidx.compose.animation.core.*
import androidx.compose.animation.transition
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.ripple.RippleIndication
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.drawLayer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview

// https://dribbble.com/shots/2779833-Google-Fonts

enum class ButtonState {
    PLUS, MINUS, F
}

val googleFontColor = Color(0xFFFE5353)

val rotateZ = FloatPropKey("rotate_z")
val textColor = ColorPropKey(label = "text_color")
val radius = DpPropKey(label = "radius")
val fontSize = IntPropKey("font_size")
val textOffsetY = DpPropKey("text_offset_y")

@Composable
fun GoogleFonts(modifier: Modifier = Modifier) {
    val buttonState = remember { mutableStateOf(ButtonState.PLUS) }
    var text = remember { mutableStateOf("+") }

    val transition = transition(
        definition = transitionDefinition {
            state(ButtonState.PLUS) {
                this[rotateZ] = 0f
                this[radius] = 0.dp
                this[textColor] = Color.White
                this[fontSize] = 90
                this[textOffsetY] = (-15).dp
            }
            state(ButtonState.MINUS) {
                this[rotateZ] = 180f
                this[radius] = 80.dp
                this[textColor] = googleFontColor
                this[fontSize] = 90
                this[textOffsetY] = (-15).dp
            }
            state(ButtonState.F) {
                this[rotateZ] = 360f
                this[radius] = 0.dp
                this[textColor] = Color.White
                this[fontSize] = 80
                this[textOffsetY] = (-5).dp
            }

            transition(ButtonState.PLUS to ButtonState.MINUS,
                ButtonState.MINUS to ButtonState.F,
                ButtonState.F to ButtonState.PLUS
            ) {
                rotateZ using tween(durationMillis = 250, easing = FastOutSlowInEasing)
                radius using tween(durationMillis = 100, easing = FastOutSlowInEasing)
                textColor using tween(durationMillis = 100, easing = FastOutSlowInEasing)
                fontSize using tween(durationMillis = 500, easing = FastOutSlowInEasing)
                textOffsetY using tween(durationMillis = 500, easing = FastOutSlowInEasing)
            }

        },
        initState = ButtonState.PLUS,
        toState = buttonState.value
    )

    Box(modifier = modifier) {
        GoogleFontView(state = transition, text = text.value) {
            buttonState.value = when(buttonState.value) {
                ButtonState.PLUS -> {
                    text.value = "-"
                    ButtonState.MINUS
                }
                ButtonState.MINUS -> {
                    text.value = "F"
                    ButtonState.F
                }
                ButtonState.F -> {
                    text.value = "+"
                    ButtonState.PLUS
                }
            }
        }
    }
}

@Composable
fun GoogleFontView(
    text: String,
    state: TransitionState,
    onClick: () -> Unit
) {
    Box(modifier = Modifier.clickable(onClick = { onClick.invoke() }, indication = RippleIndication(radius = 0.dp))) {
        Box(
            modifier = Modifier.size(size = 100.dp)
                .drawLayer(rotationZ = state[rotateZ])
        ) {
            Circle(
                radius = 100.dp,
                color = googleFontColor,
                modifier = Modifier.align(alignment = Alignment.Center)
            )
            Circle(
                radius = state[radius],
                color = Color.White,
                modifier = Modifier.align(alignment = Alignment.Center)
            )
            Text(
                text = text,
                color = state[textColor],
                fontSize = state[fontSize].sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.align(alignment = Alignment.Center)
                    .background(color = Color.Transparent)
                    .offset(y = state[textOffsetY])
            )
        }
    }
}

@Composable
@Preview
private fun previewGoogleFonts() {
    Box(modifier = Modifier.fillMaxSize()) {
        GoogleFonts(modifier = Modifier.align(alignment = Alignment.Center))
    }
}
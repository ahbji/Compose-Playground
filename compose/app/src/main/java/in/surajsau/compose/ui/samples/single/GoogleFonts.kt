package `in`.surajsau.compose.ui.samples.single

import `in`.surajsau.compose.ui.component.Circle
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class GoogleButtonState {
    PLUS, MINUS, F
}

val GoogleFontColor = Color(0xFFFE5353)

@Composable
fun GoogleFontsAnimationScreen() {

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleFonts(modifier = Modifier.align(alignment = Alignment.Center))
    }
}

@Composable
fun GoogleFonts(modifier: Modifier = Modifier) {
    val buttonState = remember { mutableStateOf(GoogleButtonState.PLUS) }
    val text = remember { mutableStateOf("+") }

    val transition = updateTransition(buttonState, "button_state_transition")

    val rotateZ = transition.animateFloat(
        label = "rotate_z",
        transitionSpec = { tween(durationMillis = 250, easing = FastOutSlowInEasing) }) {
        when (it.value) {
            GoogleButtonState.PLUS -> 0f
            GoogleButtonState.MINUS -> 180f
            GoogleButtonState.F -> 360f
        }
    }

    val textOffsetY = transition.animateDp(
        label = "text_offset_y",
        transitionSpec = { tween(durationMillis = 500, easing = FastOutSlowInEasing) }) {
        when (it.value) {
            GoogleButtonState.PLUS -> (-15).dp
            GoogleButtonState.MINUS -> (-15).dp
            GoogleButtonState.F -> (-5).dp
        }
    }

    val radius = transition.animateDp(
        label = "radius",
        transitionSpec = { tween(durationMillis = 100, easing = FastOutSlowInEasing) }) {
        when (it.value) {
            GoogleButtonState.PLUS -> 0.dp
            GoogleButtonState.MINUS -> 80.dp
            GoogleButtonState.F -> 0.dp
        }
    }

    val fontSize = transition.animateInt(
        label = "fontSize",
        transitionSpec = { tween(durationMillis = 500, easing = FastOutSlowInEasing) }) {
        when (it.value) {
            GoogleButtonState.PLUS -> 90
            GoogleButtonState.MINUS -> 90
            GoogleButtonState.F -> 80
        }
    }

    val textColor = transition.animateColor(
        label = "text_color",
        transitionSpec = { tween(durationMillis = 100, easing = FastOutSlowInEasing) }) {
        when (it.value) {
            GoogleButtonState.PLUS -> Color.White
            GoogleButtonState.MINUS -> GoogleFontColor
            GoogleButtonState.F -> Color.White
        }
    }

    Box(modifier = modifier) {
        GoogleFontView(
            rotateZ = rotateZ.value,
            textColor = textColor.value,
            textOffsetY = textOffsetY.value,
            radius = radius.value,
            fontSize = fontSize.value,
            text = text.value
        ) {
            buttonState.value = when(buttonState.value) {
                GoogleButtonState.PLUS -> {
                    text.value = "-"
                    GoogleButtonState.MINUS
                }
                GoogleButtonState.MINUS -> {
                    text.value = "F"
                    GoogleButtonState.F
                }
                GoogleButtonState.F -> {
                    text.value = "+"
                    GoogleButtonState.PLUS
                }
            }
        }
    }
}

@Composable
fun GoogleFontView(
    text: String,
    rotateZ: Float,
    radius: Dp,
    textOffsetY: Dp,
    fontSize: Int,
    textColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick
            )
    ) {
        Box(
            modifier = Modifier
                .size(size = 100.dp)
                .rotate(rotateZ)
        ) {
            Circle(
                radius = 100.dp,
                color = GoogleFontColor,
                modifier = Modifier.align(alignment = Alignment.Center)
            )
            Circle(
                radius = radius,
                color = Color.White,
                modifier = Modifier.align(alignment = Alignment.Center)
            )
            Text(
                text = text,
                color = textColor,
                fontSize = fontSize.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .background(color = Color.Transparent)
                    .offset(y = textOffsetY)
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
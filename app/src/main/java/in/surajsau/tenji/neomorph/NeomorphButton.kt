package `in`.surajsau.tenji.neomorph

import `in`.surajsau.tenji.androidx.Shape
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonConstants
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

@Composable
fun NeomorphButton() {
    val buttonState = remember { mutableStateOf(ButtonState.IDLE) }

    Box(modifier = Modifier.size(size = 70.dp)) {
        Shape(
                shape = CircleShape,
                size = Size(
                        width = 60f,
                        height = 60f
                ),
                backgroundColor = NeomorphDarkColor,
                modifier = Modifier.offset(
                        x = 10.dp,
                        y = 10.dp
                )
        )

        Shape(
                shape = CircleShape,
                size = Size(
                        width = 60f,
                        height = 60f
                ),
                backgroundColor = NeomorphLightColor
        )
        Button(
                onClick = {
                    buttonState.value = if (buttonState.value == ButtonState.PRESSED) {
                        ButtonState.IDLE
                    } else {
                        ButtonState.PRESSED
                    }
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
    NeomorphButton()
}
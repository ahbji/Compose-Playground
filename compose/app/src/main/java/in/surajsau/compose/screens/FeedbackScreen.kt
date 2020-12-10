package `in`.surajsau.compose.screens

import androidx.compose.animation.VectorConverter
import androidx.compose.animation.animate
import androidx.compose.animation.animatedColor
import androidx.compose.animation.core.AnimationVector
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.transition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import androidx.compose.ui.graphics.colorspace.Rgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FeedbackScreen() {
    val slideValue = remember { mutableStateOf(0f) }
    val color = remember { mutableStateOf(Color.Gray) }

    val converter = Color.VectorConverter(ColorSpaces.LinearExtendedSrgb)

    val startColor = converter.convertToVector(Color.Gray)
    val endColor = converter.convertToVector(Color.Blue)

    Column(
            modifier = Modifier.fillMaxSize()
                    .background(color = color.value),
            verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Text(text = "How was your ride?",
                fontSize = 80.sp,
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                        .padding(all = 16.dp)
        )

        Text(text = when(slideValue.value.toInt()) {
                    0 -> "Bad"
                    1 -> "Good"
                    2 -> "Very Good!"
                    else -> ".."
                },
                fontSize = 30.sp,
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                        .padding(all = 16.dp)
        )

        Slider(
                value = slideValue.value,
                onValueChange = {
                    val delta = it - it.toInt()
                    slideValue.value = it

                    color.value = converter.convertFromVector(AnimationVector(
                            v1 = (startColor.v1) + ((endColor.v1 - startColor.v1) * delta),
                            v2 = (startColor.v2) + ((endColor.v2 - startColor.v2) * delta),
                            v3 = (startColor.v3) + ((endColor.v3 - startColor.v3) * delta),
                            v4 = (startColor.v4) + ((endColor.v4 - startColor.v4) * delta)
                    ))

                },
                valueRange = 0f..2f
        )
    }
}

@Composable
@Preview
fun previewFeedbackScreen() {
    FeedbackScreen()
}
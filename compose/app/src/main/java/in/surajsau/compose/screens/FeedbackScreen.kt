package `in`.surajsau.compose.screens

import `in`.surajsau.compose.androidx.rememberColorVectorConverter
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
import androidx.compose.runtime.derivedStateOf
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
import kotlin.math.floor

sealed class Feedback(val color: Color, val text: String) {
    object Bad: Feedback(Color(red= 0.9921568627f, green= 0.7450980392f, blue= 0.9215686275f), "Bad")
    object Ok: Feedback(Color(red= 0.9921568627f, green= 0.9333333333f, blue= 0.7450980392f), "Ok")
    object Good: Feedback(Color(red= 0.7450980392f, green= 0.9921568627f, blue= 0.8980392157f), "Good")
}

val feedbacks = listOf(Feedback.Bad, Feedback.Ok, Feedback.Good)

@Composable
fun FeedbackScreen() {
    val slideValue = remember { mutableStateOf(1f) }
    val color = remember { mutableStateOf(Feedback.Ok.color) }

    val currentFeedback = derivedStateOf { feedbacks[slideValue.value.toInt()] }

    val converter = rememberColorVectorConverter()

    Column(
            modifier = Modifier.fillMaxSize()
                    .background(color = color.value),
            verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Text(text = "How was your ride?",
                fontSize = 60.sp,
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                        .padding(all = 16.dp)
        )

        Text(text = currentFeedback.value.text,
                fontSize = 30.sp,
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                        .padding(all = 16.dp)
        )

        Face(slideValue = slideValue.value,
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        )

        Slider(
                value = slideValue.value,
                onValueChange = {
                    val delta = it - it.toInt()
                    slideValue.value = it

                    val startColor = converter.convertToVector(feedbacks[it.toInt()].color)
                    val endColor = converter.convertToVector(feedbacks[minOf(it.toInt() + 1, 2)].color)

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
fun Face(
        slideValue: Float,
        modifier: Modifier = Modifier
) {

}

@Composable
@Preview
fun previewFeedbackScreen() {
    FeedbackScreen()
}
package `in`.surajsau.compose.ui.samples.single

import `in`.surajsau.compose.ui.component.rememberColorVectorConverter
import androidx.compose.animation.core.AnimationVector
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class FeedbackItem(val color: Color, val text: String) {
    Hideous(Color(242, 147, 145), "Hideous"),
    Bad(Color(255, 197, 238), "Bad"),
    Meh(Color(253, 220, 186), "Meh"),
    Good(Color(198, 253, 233), "Good"),
    VeryGood(Color(172, 251, 252), "Very Good");

    companion object {
        fun at(index: Int): FeedbackItem {
            if (index >= values().size)
                return values().last()
            return values().getOrNull(index)
                ?: throw IllegalArgumentException("Invalid index: $index")
        }
    }
}

@Composable
fun FeedbackScreen() {
    val slideValue = remember { mutableStateOf(1f) }
    val color = remember { mutableStateOf(FeedbackItem.Good.color) }

    val currentFeedback = derivedStateOf { FeedbackItem.at(slideValue.value.toInt()) }

    val converter = rememberColorVectorConverter()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = color.value),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Text(
            text = "How was your ride?",
            fontSize = 60.sp,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(all = 16.dp)
        )

        Text(
            text = currentFeedback.value.text,
            fontSize = 30.sp,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(all = 16.dp)
        )

        Slider(
            value = slideValue.value,
            onValueChange = {
                val delta = it - it.toInt()
                slideValue.value = it

                val startColor = converter.convertToVector(FeedbackItem.at(it.toInt()).color)
                val endColor = converter.convertToVector(FeedbackItem.at(it.toInt() + 1).color)

                color.value = converter.convertFromVector(
                    AnimationVector(
                        v1 = (startColor.v1) + ((endColor.v1 - startColor.v1) * delta),
                        v2 = (startColor.v2) + ((endColor.v2 - startColor.v2) * delta),
                        v3 = (startColor.v3) + ((endColor.v3 - startColor.v3) * delta),
                        v4 = (startColor.v4) + ((endColor.v4 - startColor.v4) * delta)
                    )
                )

            },
            valueRange = 0f..FeedbackItem.values().size.toFloat(),
            steps = FeedbackItem.values().size - 2,
        )
    }
}
package `in`.surajsau.compose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RotatingOnboarding() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(shape = GenericShape {
            })
    ) {
        Box(
            modifier = Modifier
                .size(width = 100.dp, height = 100.dp)
                .align(alignment = Alignment.BottomCenter)
                .offset(y = (-100).dp)
                .clip(shape = GenericShape {
                    moveTo(0f, 50f)
                    quadraticBezierTo(50f, 100f, 100f, 50f)
                    quadraticBezierTo(100f, 50f, 50f, 0f)
                })
                .background(color = Color.Black)
        )
    }
}

@Composable
@Preview
fun previewRotatingOnboarding() {
    RotatingOnboarding()
}
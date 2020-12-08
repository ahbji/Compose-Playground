package `in`.surajsau.tenji.androidx

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.drawLayer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RadialShadow(
        size: Size,
        shadowColor: Color = Color.Gray,
        modifier: Modifier = Modifier
) {
    Box(
            modifier = modifier
                .size(width = size.width, height = size.height)
                .clipToBounds()
    ) {
        GradientCircle(
                radius = size.width,
                colors = listOf(shadowColor, Color.Transparent)
        )
    }
}

@Preview
@Composable
private fun PreviewRadialShadow() {
    Box(
            modifier = Modifier.fillMaxWidth()
                    .height(height = 70.dp)
                    .background(color = Color.White)
    ) {
        RadialShadow(
                size = Size(
                    width = 200.dp,
                    height = 50.dp
                ),
            shadowColor = Color.Red,
            modifier = Modifier.align(alignment = Alignment.BottomCenter)
        )
    }
}
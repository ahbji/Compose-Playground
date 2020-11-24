package `in`.surajsau.tenji.androidx

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradient
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview

@Composable
fun Shape(
        shape: Shape,
        size: Size,
        brush: Brush,
        modifier: Modifier = Modifier
){
    Column(modifier = Modifier.wrapContentSize(Alignment.Center)) {
        Box(
                modifier = modifier
                        .preferredSize(
                                width = size.width,
                                height = size.height
                        )
                        .background(
                                brush = brush,
                                shape = shape
                        )
        )
    }
}

@Composable
fun Circle(
        radius: Dp,
        color: Color,
        modifier: Modifier = Modifier
) {
    Shape(
            shape = CircleShape,
            size = Size(
                    width = radius,
                    height = radius
            ),
            brush = SolidColor(value = color),
            modifier = modifier
    )
}

@Composable
fun GradientCircle(
        radius: Dp,
        colors: List<Color>,
        modifier: Modifier = Modifier
) {
    Shape(
            shape = CircleShape,
            size = Size(size = radius),
            brush = RadialGradient(
                    colors = colors,
                    centerY = radius.value * 1.3f,
                    centerX = radius.value * 1.3f,
                    radius = radius.value * 1.3f
            ),
            modifier = modifier
    )
}

@Preview
@Composable
fun PreviewShape() {
    Box {
        GradientCircle(
                radius = 100.dp,
                colors = listOf(Color.White, Color.Transparent)
        )

        Circle(
                radius = 60.dp,
                color = Color.Red
        )
    }
}
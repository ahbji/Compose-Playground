package `in`.surajsau.compose.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun Circle(
    radius: Dp,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.primary,
) {

    Box(
        modifier = modifier
            .size(radius * 2)
            .background(color = color, shape = CircleShape)
    )

}
package `in`.surajsau.tenji.androidx

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun Shape(
        shape: Shape,
        size: Size,
        backgroundColor: Color = Color.White,
        modifier: Modifier = Modifier
){
    Column(modifier = Modifier.wrapContentSize(Alignment.Center)) {
        Box(
                modifier = modifier
                        .preferredSize(
                                width = size.width.dp,
                                height = size.height.dp
                        )
                        .clip(shape = shape)
                        .background(color = backgroundColor)
        )
    }
}
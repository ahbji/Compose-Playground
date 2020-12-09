package `in`.surajsau.compose.simple

import `in`.surajsau.compose.ui.progressIconColor
import androidx.annotation.FloatRange
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import `in`.surajsau.compose.R

@Composable
fun GenshinSimpleLoader(
        @FloatRange(from = 0.0, to = 100.0) progress: Float,
        defaultColour: Color = progressIconColor
) {
    Box {
        Row {
            listOf(
                    R.drawable.anemo,
                    R.drawable.cryo,
                    R.drawable.dendro,
                    R.drawable.electro,
                    R.drawable.geo,
                    R.drawable.hydro,
                    R.drawable.pyro
            ).forEach { imageRes ->
                Box(modifier = Modifier.size(size = 50.dp)) {
                    Image(
                            bitmap = imageResource(id = imageRes),
                            modifier = Modifier.fillMaxSize(),
                            colorFilter = ColorFilter.tint(defaultColour)
                    )
                }
            }
        }
        Box(modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(
                        width = (350.times(1f - (progress/100f))).dp,
                        height = 50.dp
                )
                .clip(shape = RectangleShape)
                .background(
                        brush = SolidColor(value = Color.White),
                        alpha = 0.7f
                )
        )
    }
}

@Preview
@Composable
fun previewGenshinSimpleLoader() {
    GenshinSimpleLoader(progress = 10f)
}
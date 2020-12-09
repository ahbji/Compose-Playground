package `in`.surajsau.compose.genshin

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import `in`.surajsau.compose.R

@Composable
fun GenshinLoader(
    @FloatRange(from = 0.0, to = 100.0) progress: Float
) {
    Row {
        listOf(
            R.drawable.anemo,
            R.drawable.cryo,
            R.drawable.dendro,
            R.drawable.electro,
            R.drawable.geo,
            R.drawable.hydro,
            R.drawable.pyro
        ).forEachIndexed { index, imageRes ->
            val from = 100f/7 * index
            val to = 100f/7 * (index + 1)
            val fraction = when {
                progress > to -> 1f
                progress > from -> (progress - from)/(to - from)
                else -> 0f
            }
            GenshinIcon(
                fraction = fraction,
                image = imageResource(id = imageRes),
                size = 50.dp
            )
        }
    }
}

@Preview
@Composable
fun previewGenshinLoader() {
    GenshinLoader(progress = 38.160786f)
}
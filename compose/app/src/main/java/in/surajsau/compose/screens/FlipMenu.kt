package `in`.surajsau.compose.screens

import androidx.compose.animation.animatedFloat
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * reference: [https://dribbble.com/shots/4773637-3D-flip-menu]
*/

val darkColor = Color(0xFF958771)
val mediumColor = Color(0xFFB3A58E)
val lightColor = Color(0xFFDAD1A6)

@Composable
fun FlipMenu(modifier: Modifier = Modifier) {
    val rotate = animatedFloat(initVal = 0f)
    Box(modifier = modifier) {
        Box(
                modifier = Modifier
                        .size(size = 100.dp)
                        .clip(shape = GenericShape {
                            val w = it.width
                            val h = it.height
                            relativeMoveTo(0f, 0f)
                            relativeLineTo(0f, h)
                            relativeLineTo(w, 0f)
                            relativeLineTo(0f, -h)
                        })
                        .background(color = lightColor)
                        .clickable(onClick = {
                            rotate.animateTo(targetValue = Math.PI.toFloat()/2)
                        })
        )
    }
}

@Preview
@Composable
fun previewFlipMenu() {
    Box(modifier = Modifier.background(color = darkColor).fillMaxSize()) {
        FlipMenu(modifier = Modifier.align(alignment = Alignment.Center))
    }
}
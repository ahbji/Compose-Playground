package `in`.surajsau.compose.screens

import androidx.compose.animation.animatedFloat
import androidx.compose.animation.core.AnimationConstants
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.ripple.rememberRippleIndication
import androidx.compose.runtime.Composable
import androidx.compose.runtime.onActive
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * https://dribbble.com/shots/3074660-Gmail-for-iOS-Animated-Icon
 */

@Composable
fun Gmail(modifier: Modifier = Modifier) {
    val bounce = animatedFloat(initVal = 0f)

    Box(modifier = modifier
            .size(width = 150.dp, height = 50.dp)
            .clip(shape = GenericShape {
                val height = it.height * bounce.value
                relativeMoveTo(0f, 0f)
                relativeLineTo(it.width / 2, height)
                relativeLineTo(it.width / 2, -height)
            })
            .background(color = Color.Red)
            .clickable(onClick = {
                bounce.animateTo(
                        targetValue = 1f,
                        anim = spring(
                                dampingRatio = Spring.DampingRatioMediumBouncy,
                                stiffness = Spring.StiffnessLow
                        ),
                )
            })
    )
}

@Preview
@Composable
fun previewGmail() {
    Box(modifier = Modifier.fillMaxSize()) {
        Gmail(modifier = Modifier.align(alignment = Alignment.Center))
    }
}
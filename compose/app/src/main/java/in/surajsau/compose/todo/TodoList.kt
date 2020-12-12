package `in`.surajsau.compose.todo

import androidx.compose.animation.animatedFloat
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// https://dribbble.com/shots/3959132-Todo-List-Swipe-To-Check

@Composable
fun StrikeAnimation(modifier: Modifier = Modifier) {
    ConstraintLayout(modifier = modifier) {
        var isStrikeThrough = remember { false }
        val (strike, text, tick) = createRefs()

        val headAnimation = animatedFloat(initVal = 0f)
        val tailAnimation = animatedFloat(initVal = 0f)
        
        Strike(
                headAnimation = headAnimation.value,
                tailAnimation = tailAnimation.value,
                modifier = Modifier
                        .constrainAs(strike) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
        )

        TickAnimation(
                headAnimation = 1f,
                tailAnimation = 1f,
                modifier = Modifier
                        .constrainAs(tick) {
                            start.linkTo(strike.start)
                            top.linkTo(strike.top)
                            bottom.linkTo(strike.bottom)
                        }
        )
        
        Text(text = "Do an important task",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(text) {
                    start.linkTo(parent.start, margin = 60.dp)
                    top.linkTo(strike.top)
                    bottom.linkTo(strike.bottom)
                }.clickable(onClick = {
                    isStrikeThrough = !isStrikeThrough
                    headAnimation.animateTo(
                            targetValue = if(isStrikeThrough) 1f else 0f,
                            anim = tween(durationMillis = 1000, easing = LinearEasing)
                    )

                    tailAnimation.animateTo(
                            targetValue = if(isStrikeThrough) 1f else 0f,
                            anim = tween(durationMillis = 1000, easing = LinearEasing)
                    )
                })
        )
    }
}

@Preview
@Composable
fun previewStrikeAnimation() {
    Box(modifier = Modifier.fillMaxSize()) {
        StrikeAnimation(modifier = Modifier.fillMaxSize())
    }
}
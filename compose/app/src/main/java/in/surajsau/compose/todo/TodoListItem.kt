package `in`.surajsau.compose.todo

import androidx.compose.animation.animatedColor
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

val strikeColor = Color(0xFF04B7FE)
val strikeTextColor = Color(0xFF15CBF2)
val normalTextColor = Color(0xFF31595D)

@Composable
fun TodoListItem(
        text: String,
        modifier: Modifier = Modifier,
        onStrikeChange: ((Boolean) -> Unit)? = null
) {
    ConstraintLayout(modifier = modifier) {
        var isStrikeThrough = remember { false }
        val (strike, textField, tick) = createRefs()

        val headAnimation = animatedFloat(initVal = 0f)
        val tailAnimation = animatedFloat(initVal = 0f)

        val tickHeadAnimation = animatedFloat(initVal = 0f)
        val tickTailAnimation = animatedFloat(initVal = 0f)

        val textColor = animatedColor(initVal = normalTextColor)

        Text(text = text,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = textColor.value,
                modifier = Modifier.constrainAs(textField) {
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
                            anim = tween(durationMillis = 500, easing = LinearEasing)
                    )

                    textColor.animateTo(
                            targetValue = if(isStrikeThrough) strikeTextColor else normalTextColor,
                            anim = tween(durationMillis = 300, delayMillis = 700, easing = LinearEasing)
                    )

                    tickHeadAnimation.animateTo(
                            targetValue = if(isStrikeThrough) 1f else 0f,
                            anim = tween(delayMillis = 600, durationMillis = 500, easing = LinearEasing)
                    )

                    tickTailAnimation.animateTo(
                            targetValue = if(isStrikeThrough) 1f else 0f,
                            anim = tween(delayMillis = 600, durationMillis = 500, easing = LinearEasing)
                    )
                })
        )
        
        Strike(
                color = strikeColor,
                strokeWidth = 3f,
                curveRadius = 4.dp,
                sideLength = 24.dp,
                headAnimation = headAnimation.value,
                tailAnimation = tailAnimation.value,
                modifier = Modifier
                        .constrainAs(strike) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start, margin = 20.dp)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }
        )

        TickAnimation(
                sparkleRadius = 50.dp,
                tickSize = 15.dp,
                color = strikeColor,
                headAnimation = tickHeadAnimation.value,
                tailAnimation = tickTailAnimation.value,
                modifier = Modifier
                        .constrainAs(tick) {
                            start.linkTo(parent.start)
                            top.linkTo(strike.top)
                            bottom.linkTo(strike.bottom)
                        }
        )
    }
}
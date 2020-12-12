package `in`.surajsau.compose.todo

import androidx.compose.animation.animatedColor
import androidx.compose.animation.animatedFloat
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.gesture.Direction
import androidx.compose.ui.gesture.DragObserver
import androidx.compose.ui.gesture.dragGestureFilter
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// https://dribbble.com/shots/3959132-Todo-List-Swipe-To-Check

// TODO: curve the right bottom half side when dragging

val strikeColor = Color(0xFF04B7FE)
val strikeTextColor = Color(0xFF15CBF2)
val normalTextColor = Color(0xFF31595D)

@Composable
fun TodoListItem(
        text: String,
        modifier: Modifier = Modifier,
        onStrikeChange: ((Boolean) -> Unit)? = null
) {

    val drag = remember { mutableStateOf(0f) }

    val headAnimation = animatedFloat(initVal = 0f)
    val tailAnimation = animatedFloat(initVal = 0f)

    val tickHeadAnimation = animatedFloat(initVal = 0f)
    val tickTailAnimation = animatedFloat(initVal = 0f)

    val textColor = animatedColor(initVal = normalTextColor)

    ConstraintLayout(
            modifier = modifier
                    .dragGestureFilter(object : DragObserver {
                        override fun onDrag(dragDistance: Offset): Offset {
                            drag.value += dragDistance.x

                            if(drag.value < 200f) {
                                headAnimation.snapTo(targetValue = (drag.value) / 500f)
                                tailAnimation.snapTo(targetValue = (drag.value) / 500f)
                            } else {
                                headAnimation.animateTo(
                                        targetValue = 1f,
                                        anim = tween(
                                                durationMillis = 1000,
                                                easing = LinearEasing
                                        )
                                )

                                tailAnimation.animateTo(
                                        targetValue = 1f,
                                        anim = tween(
                                                durationMillis = 500,
                                                easing = LinearEasing
                                        )
                                )

                                textColor.animateTo(
                                        targetValue = strikeTextColor,
                                        anim = tween(
                                                durationMillis = 300,
                                                delayMillis = 700,
                                                easing = LinearEasing
                                        )
                                )

                                tickHeadAnimation.animateTo(
                                        targetValue = 1f,
                                        anim = tween(
                                                delayMillis = 600,
                                                durationMillis = 500,
                                                easing = LinearEasing
                                        )
                                )

                                tickTailAnimation.animateTo(
                                        targetValue = 1f,
                                        anim = tween(
                                                delayMillis = 600,
                                                durationMillis = 500,
                                                easing = LinearEasing
                                        )
                                )
                            }
                            return super.onDrag(dragDistance)
                        }
                    }, canDrag = { it == Direction.RIGHT })
    ) {
        val (strike, textField, tick) = remember { createRefs() }

        Text(text = text,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = textColor.value,
                modifier = Modifier.constrainAs(textField) {
                    start.linkTo(parent.start, margin = 60.dp)
                    top.linkTo(strike.top)
                    bottom.linkTo(strike.bottom)
                }
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
package `in`.surajsau.tenji.spring

import android.util.Log
import androidx.compose.animation.DpPropKey
import androidx.compose.animation.animate
import androidx.compose.animation.core.*
import androidx.compose.animation.transition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonConstants
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.drawLayer
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.gesture.Direction
import androidx.compose.ui.gesture.DragObserver
import androidx.compose.ui.gesture.dragGestureFilter
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.example.genshinloader.R
import kotlin.math.abs

enum class DragState {
    DRAGGING, END
}

val appBarHeight = DpPropKey("appBarHeight")
val hillOffset = DpPropKey("hillOffset")
val rocketRotationIcon = FloatPropKey("rocketRotation")

@Composable
fun SpringScreen() {
    Column(
            modifier = Modifier.fillMaxSize()
                    .background(color = Color(0xFFF1F3F5))
    ) {
        ConstraintLayout(
                modifier = Modifier.fillMaxWidth().wrapContentHeight()
        ) {
            val (button, appBar, paperPlane) = createRefs()

            val dragState = remember { mutableStateOf(DragState.END) }
            val height = remember { mutableStateOf(200.dp) }

            val transition = transition(definition = transitionDefinition {
               state(DragState.DRAGGING) {
                   this[appBarHeight] = height.value
//                   this[hillOffset] = (height.value - 200.dp) * 0.3f
                   this[rocketRotationIcon] = (height.value - 200.dp)/200.dp * 15f
               }
               state(DragState.END) {
                   this[appBarHeight] = 200.dp
//                   this[hillOffset] = 0.dp
                   this[rocketRotationIcon] = 0f
               }

                transition(DragState.DRAGGING to DragState.END) {
                    appBarHeight using spring(
                        stiffness = Spring.StiffnessLow,
                        dampingRatio = Spring.DampingRatioMediumBouncy
                    )

                    rocketRotationIcon using spring(
                        stiffness = Spring.StiffnessLow,
                        dampingRatio = Spring.DampingRatioMediumBouncy
                    )

//                    hillOffset using spring(
//                        stiffness = Spring.StiffnessLow,
//                        dampingRatio = Spring.DampingRatioLowBouncy
//                    )
                }
            }, initState = DragState.DRAGGING, toState = dragState.value)

            AppBar(
                height = transition,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(appBar) {
                        top.linkTo(parent.top)
                        bottom.linkTo(button.bottom, margin = 25.dp)
                    },
                onDragEnded = {
                    dragState.value = DragState.END
                },
                onDragging = {
                    dragState.value = DragState.DRAGGING
                    height.value += (it.y * 0.1f).dp
                },
                onDragStarted = {
                    height.value = 200.dp
                }
            ) {
//                Hills(
//                    state = transition,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .align(alignment = Alignment.BottomCenter)
//                        .height(height = 100.dp)
//                )
            }

            val buttonStartGuide = createGuidelineFromStart(offset = 16.dp)
            FloatingButton(
                modifier = Modifier
                    .size(size = 50.dp)
                    .constrainAs(button) {
                        start.linkTo(buttonStartGuide)
                        bottom.linkTo(parent.bottom)
                    }
            )

            val iconBottomGuide = createGuidelineFromBottom(offset = 12.dp)
            val iconStartGuide = createGuidelineFromStart(offset = 30.dp)
            RocketIcon(
                state = transition,
                modifier = Modifier
                    .wrapContentSize()
                    .constrainAs(paperPlane) {
                        start.linkTo(iconStartGuide)
                        bottom.linkTo(iconBottomGuide)
                    }
            )

        }

        LazyColumnFor(
                items = (0..5).map { Item(title = "Title $it", description = "Item description $it") }
        ) { item ->
            Column(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
                Text(
                        text = item.title,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp)
                )
                Text(
                        text = item.description,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                )
            }
        }
    }
}

@Composable
fun Hills(
    state: TransitionState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(shape = HillShape)
            .offset(
                x = 0.dp,
                y = state[hillOffset]
            )
            .background(color = Color(0xFF3F6274))
    )
}

@Composable
fun FloatingButton(
    modifier: Modifier = Modifier
) {
    Button(
        onClick = {},
        colors = ButtonConstants.defaultButtonColors(Color(0xFF83B1DF)),
        modifier = modifier
                . clip (shape = CircleShape)
    ){}
}

@Composable
fun RocketIcon(
    state: TransitionState,
    modifier: Modifier = Modifier
) {
    Image(
        asset = vectorResource(id = R.drawable.ic_paper_plane),
        modifier = modifier
            .drawLayer(rotationZ = state[rocketRotationIcon])
    )
}

@Composable
fun AppBar(
    height: TransitionState,
    onDragEnded: () -> Unit,
    onDragging: (Offset) -> Unit,
    onDragStarted: () -> Unit,
    modifier: Modifier = Modifier,
    children: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .height(height = height[appBarHeight])
            .background(color = Color(0xFF7DCFCC))
            .clipToBounds()
            .dragGestureFilter(object : DragObserver {
                override fun onStop(velocity: Offset) {
                    super.onStop(velocity)
                    onDragEnded()
                }

                override fun onDrag(dragDistance: Offset): Offset {
                    onDragging(dragDistance)
                    return dragDistance
                }

                override fun onStart(downPosition: Offset) {
                    super.onStart(downPosition)
                    onDragStarted()
                }
            }),
        children = children
    )
}

data class Item(
        val title: String,
        val description: String
)

@Composable
@Preview
fun previewSpringScreen() {
    SpringScreen()
}
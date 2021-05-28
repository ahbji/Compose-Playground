package `in`.surajsau.compose.spring

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import `in`.surajsau.compose.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.constraintlayout.widget.ConstraintLayout

enum class DragState {
    DRAGGING, END, NONE
}

val appBarHeight = DpPropKey("appBarHeight")
val hillOffset = DpPropKey("hillOffset")
val rocketRotationIcon = FloatPropKey("rocketRotation")

val flightProgress = FloatPropKey("flight_progress")

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

            val y = animatedFloat(initVal = 200f)

            val planeRotation = animatedFloat(initVal = 0f)

            val yOffset = animatedFloat(initVal = 0f)
            val xOffset = animatedFloat(initVal = 0f)

            AppBar(
                height = y.value.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(appBar) {
                        top.linkTo(parent.top)
                        bottom.linkTo(button.bottom, margin = 25.dp)
                    },
                onDragEnded = {
                      xOffset.animateTo(
                              targetValue = 300f,
                              anim = tween(
                                      durationMillis = 400,
                                      easing = FastOutLinearInEasing,
                              )
                      ) { _, _ ->
                          planeRotation.animateTo(0f, anim = tween(
                                  durationMillis = 600,
                                  delayMillis = 1000,
                                  easing = LinearOutSlowInEasing,
                          ))

                          xOffset.animateTo(-60f, anim = tween(
                                  durationMillis = 600,
                                  delayMillis = 1000,
                                  easing = LinearOutSlowInEasing,
                          ))
                      }
                        yOffset.animateTo(
                                targetValue = -500f,
                                anim = tween(
                                        durationMillis = 400,
                                        easing = FastOutLinearInEasing,
                                )
                        ) { _, _ ->
                            yOffset.animateTo(0f, anim = tween(
                                    durationMillis = 600,
                                    delayMillis = 1000,
                                    easing = FastOutLinearInEasing,
                            ))
                        }
                      y.animateTo(
                              targetValue = 200f,
                              anim = spring(
                                      dampingRatio = Spring.DampingRatioHighBouncy,
                                      stiffness = 800f
                              )
                      )
                },
                onDragging = {
                    planeRotation.animateTo(
                            targetValue = minOf((y.targetValue - 200f) * 45/200, 15f),
                            anim = spring(
                                    dampingRatio = Spring.DampingRatioNoBouncy,
                                    stiffness = Spring.StiffnessHigh
                            )
                    )
                    y.animateTo(
                            targetValue = y.targetValue + (it.y * 0.1f),
                            anim = spring(
                                    dampingRatio = Spring.DampingRatioNoBouncy,
                                    stiffness = Spring.StiffnessHigh
                            )
                    )
                },
                onDragStarted = {}
            ){}

            val buttonStartGuide = createGuidelineFromStart(offset = 16.dp)
            FloatingButton(
                modifier = Modifier
                    .size(size = 50.dp)
                    .constrainAs(button) {
                        start.linkTo(buttonStartGuide)
                        bottom.linkTo(parent.bottom)
                    }
            )

            RocketIcon(
                offsetState = Pair(xOffset.value, yOffset.value),
                rotationZ = planeRotation.value,
                modifier = Modifier
                    .wrapContentSize()
                    .constrainAs(paperPlane) {
                        start.linkTo(button.start)
                        end.linkTo(button.end)
                        bottom.linkTo(button.bottom)
                        top.linkTo(button.top)
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
    offsetState: Pair<Float, Float>,
    rotationZ: Float,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier
            .offset(
                    x = with(AmbientDensity.current) { offsetState.first.toDp() } ,
                    y = with(AmbientDensity.current) { offsetState.second.toDp() }
            )
    ) {
        Image(
                imageVector = vectorResource(id = R.drawable.ic_paper_plane),
                modifier = Modifier
                        .wrapContentSize()
                        .drawLayer(rotationZ = -rotationZ)
        )
    }
}

@Composable
fun AppBar(
    height: Dp,
    onDragEnded: () -> Unit,
    onDragging: (Offset) -> Unit,
    onDragStarted: () -> Unit,
    modifier: Modifier = Modifier,
    children: @Composable BoxScope.() -> Unit
) {
    Log.e("Appbar", "height: ${height.value}")
    Box(
        modifier = modifier
            .height(height = height)
            .background(color = Color(0xFF7DCFCC))
            .clipToBounds()
            .dragGestureFilter(object : DragObserver {
                override fun onStop(velocity: Offset) {
                    super.onStop(velocity)
                    onDragEnded()
                }

                override fun onDrag(dragDistance: Offset): Offset {
                    onDragging(dragDistance)
                    return super.onDrag(dragDistance)
                }

                override fun onStart(downPosition: Offset) {
                    super.onStart(downPosition)
                    onDragStarted()
                }
            }),
        content = children
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
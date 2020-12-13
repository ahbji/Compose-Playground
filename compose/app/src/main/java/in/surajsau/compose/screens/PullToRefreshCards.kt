package `in`.surajsau.compose.screens

import androidx.compose.animation.animatedFloat
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.gesture.Direction
import androidx.compose.ui.gesture.DragObserver
import androidx.compose.ui.gesture.dragGestureFilter
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.AmbientDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.core.graphics.scaleMatrix

// https://dribbble.com/shots/4963449-Pull-to-Refresh-Animation

@Composable
fun PullToRefreshCards() {

    val drag = animatedFloat(initVal = 0f)

    Column(
            modifier = Modifier.fillMaxSize()
                    .background(Color.Gray)
                    .dragGestureFilter(object: DragObserver {
                        override fun onDrag(dragDistance: Offset): Offset {
                            drag.snapTo(targetValue = drag.value + dragDistance.y)
                            return super.onDrag(dragDistance)
                        }

                        override fun onStop(velocity: Offset) {
                            super.onStop(velocity)
                            drag.animateTo(targetValue = 0f, anim = spring(
                                    dampingRatio = Spring.DampingRatioMediumBouncy,
                                    stiffness = Spring.StiffnessLow
                            ))
                        }
                    }, canDrag = { it == Direction.DOWN || it == Direction.UP })
    ) {
        Box(modifier = Modifier.fillMaxWidth()
                .height(
                        height = 16.dp + maxOf(
                                0.dp,
                                with(AmbientDensity.current){ (drag.value * 0.2f).toDp() }
                        )
                )
        )

        (1..5).forEach {
            Card(
                    modifier = Modifier.fillMaxWidth()
                            .zIndex(zIndex = (5 - it).toFloat())
                            .height(height = 100.dp)
                            .padding(bottom = maxOf(0.dp, 16.dp - (8.dp * drag.value * 0.01f)))
                            .clip(shape = object : Shape {
                                override fun createOutline(size: Size, density: Density): Outline {
                                    val start = 16f * density.density
                                    val end = size.width - (16f * density.density)
                                    val top = 0f
                                    val bottom = size.height
                                    val cornerRadius = 16f * density.density
                                    val bottomDelta = (8f * drag.value * 0.01f) * density.density

                                    val path = Path().apply {
                                        relativeMoveTo(start, top + cornerRadius)
                                        relativeLineTo(-bottomDelta, bottom - cornerRadius)

                                        arcTo(
                                                rect = Rect(
                                                        center = Offset(start + cornerRadius - bottomDelta, bottom - cornerRadius),
                                                        radius = cornerRadius
                                                ),
                                                startAngleDegrees = -180f,
                                                sweepAngleDegrees = -90f,
                                                forceMoveTo = false
                                        )
                                        relativeLineTo(end - cornerRadius + bottomDelta, 0f)
                                        arcTo(
                                                rect = Rect(
                                                        center = Offset(end - cornerRadius + bottomDelta, bottom - cornerRadius),
                                                        radius = cornerRadius
                                                ),
                                                startAngleDegrees = -270f,
                                                sweepAngleDegrees = -90f,
                                                forceMoveTo = false
                                        )
                                        relativeLineTo(-bottomDelta, -(bottom - cornerRadius))
                                        arcTo(
                                                rect = Rect(
                                                        center = Offset(end - cornerRadius, cornerRadius),
                                                        radius = cornerRadius
                                                ),
                                                startAngleDegrees = 0f,
                                                sweepAngleDegrees = -90f,
                                                forceMoveTo = false
                                        )
                                        relativeLineTo(-(end - cornerRadius), 0f)
                                        arcTo(
                                                rect = Rect(
                                                        center = Offset(start + cornerRadius, cornerRadius),
                                                        radius = cornerRadius
                                                ),
                                                startAngleDegrees = -90f,
                                                sweepAngleDegrees = -90f,
                                                forceMoveTo = false
                                        )
                                        close()
                                    }
                                    return Outline.Generic(path = path)
                                }
                            }),
                    elevation = 20.dp
            ) {

            }
        }
    }
}

@Composable
@Preview
fun previewPullToRefreshCards() {
    PullToRefreshCards()
}
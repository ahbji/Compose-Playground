package `in`.surajsau.compose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp

// https://dribbble.com/shots/4963449-Pull-to-Refresh-Animation

@Composable
fun PullToRefreshCards() {
    val scroll = rememberScrollState()
    LazyColumnFor(
            items = listOf(1, 2, 3, 4, 5),
            modifier = Modifier.fillMaxSize()
                    .background(Color.Gray)
                    .verticalScroll(
                            state = scroll
                    )
    ) {

        Card(
                modifier = Modifier.fillMaxWidth()
                        .height(height = 100.dp)
                        .padding(bottom = 16.dp)
                        .clip(shape = object : Shape {
                            override fun createOutline(size: Size, density: Density): Outline {
                                val path = Path().apply {
                                    relativeMoveTo(16f, 16f)
                                    relativeLineTo(0f, size.height - 32f)
                                    arcTo(
                                            rect = Rect(
                                                    center = Offset(32f, size.height - 16f),
                                                    radius = 16f
                                            ),
                                            startAngleDegrees = -180f,
                                            sweepAngleDegrees = -90f,
                                            forceMoveTo = false
                                    )
                                    relativeLineTo(size.width - 64f, 0f)
                                    arcTo(
                                            rect = Rect(
                                                    center = Offset(size.width - 32f, size.height - 16f),
                                                    radius = 16f
                                            ),
                                            startAngleDegrees = -270f,
                                            sweepAngleDegrees = -90f,
                                            forceMoveTo = false
                                    )
                                    relativeLineTo(0f, -(size.height - 32f))
                                    arcTo(
                                            rect = Rect(
                                                    center = Offset(size.width - 32f, 16f),
                                                    radius = 16f
                                            ),
                                            startAngleDegrees = 0f,
                                            sweepAngleDegrees = -90f,
                                            forceMoveTo = false
                                    )
                                    relativeLineTo(16f - (size.width - 32f), 0f)
                                    arcTo(
                                            rect = Rect(
                                                    center = Offset(32f, 16f),
                                                    radius = 16f
                                            ),
                                            startAngleDegrees = -90f,
                                            sweepAngleDegrees = -90f,
                                            forceMoveTo = false
                                    )
                                    close()
                                }
                                return Outline.Generic(path = path)
                            }
                        })
        ) {

        }
    }
}

@Composable
@Preview
fun previewPullToRefreshCards() {
    PullToRefreshCards()
}
package `in`.surajsau.tenji.spring

import android.util.Log
import androidx.compose.animation.animate
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.ConstraintSet
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.gesture.DragObserver
import androidx.compose.ui.gesture.dragGestureFilter
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.example.genshinloader.R
import kotlin.math.abs

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

            val dy = remember { mutableStateOf(200.dp) }
            val isDragging = remember { mutableStateOf(false) }

            val height = animate(
                    target = dy.value,
                    animSpec = spring(
                            dampingRatio = Spring.DampingRatioHighBouncy,
                            stiffness = 800f
                    )
            )

            Log.e("Spring", "dy: ${dy.value}, height: ${height.value}")

            Box(
                    modifier = Modifier
                            .fillMaxWidth()
                            .height(height = if (isDragging.value) dy.value else height)
                            .background(color = Color(0xFF7DCFCC))
                            .clipToBounds()
                            .dragGestureFilter(object : DragObserver {
                                override fun onStop(velocity: Offset) {
                                    super.onStop(velocity)
                                    isDragging.value = false
                                    dy.value = 200.dp
                                }

                                override fun onDrag(dragDistance: Offset): Offset {
                                    dy.value += abs(dragDistance.y * 0.1f).dp
                                    isDragging.value = true
                                    return dragDistance
                                }
                            })
                            .constrainAs(appBar) {
                                top.linkTo(parent.top)
                                bottom.linkTo(button.bottom, margin = 25.dp)
                            }
            )

            Box(
                    modifier = Modifier.size(size = 50.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color(0xFF83B1DF))
                            .constrainAs(button) {
                                start.linkTo(parent.start, margin = 16.dp)
                                bottom.linkTo(parent.bottom)
                            }
            )

            Image(
                    asset = vectorResource(id = R.drawable.ic_paper_plane),
                    modifier = Modifier
                            .drawLayer(
                                    rotationZ = ((if(isDragging.value) dy.value else height) - 200.dp).value * 0.6f
                            )
                            .constrainAs(paperPlane) {
                                start.linkTo(button.start)
                                end.linkTo(button.end)
                                top.linkTo(button.top)
                                bottom.linkTo(button.bottom)
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

data class Item(
        val title: String,
        val description: String
)

@Composable
@Preview
fun previewSpringScreen() {
    SpringScreen()
}
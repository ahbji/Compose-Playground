package `in`.surajsau.compose.screens

import androidx.compose.animation.animate
import androidx.compose.animation.animatedValue
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import `in`.surajsau.compose.R
import androidx.compose.animation.core.VectorConverter
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ripple.rememberRippleIndication
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.gesture.pressIndicatorGestureFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector

val colorBg = Color(0xFFF4DEE4)

@Composable
fun MusicPlayer(modifier: Modifier = Modifier) {

    val isPlaying = remember { mutableStateOf(false) }

    val behindCardOffset = animatedValue(initVal = 0.dp, converter = Dp.VectorConverter)

    ConstraintLayout(modifier = modifier
        .fillMaxWidth()
        .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)) {

        val (progressCard, playerCard, disc) = createRefs()

        Card(
            modifier = Modifier.fillMaxWidth(fraction = 0.9f)
                .height(height = 100.dp)
                .constrainAs(progressCard) {
                    bottom.linkTo(parent.bottom, margin = behindCardOffset.value)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            shape = RoundedCornerShape(size = 10.dp),
            backgroundColor = Color(0xA0FFFFFF),
            elevation = 0.dp,
        ){
            Column(modifier = Modifier.fillMaxWidth()
                    .padding(start = 130.dp, top = 8.dp)) {
                Text(text = "Flume", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF42295D))
                Text(text = "Flume", fontSize = 10.sp, color = Color(0xFFCFACBA))
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth()
                .height(height = 100.dp)
                .constrainAs(playerCard) {
                    bottom.linkTo(parent.bottom)
                },
            shape = RoundedCornerShape(size = 10.dp),
            elevation = 30.dp,
        ){
            Row(
                modifier = Modifier.fillMaxSize()
                            .padding(all = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Spacer(modifier = Modifier.width(120.dp))

                ControlButton(
                    imageVector = vectorResource(id = R.drawable.ic_fast_forward),
                    modifier = Modifier.align (alignment = Alignment.CenterVertically)
                        .rotate(degrees = 180f)
                ) {}

                ControlButton(imageVector = vectorResource(
                    id = if(isPlaying.value)
                        R.drawable.ic_play_button_arrowhead
                    else
                        R.drawable.ic_pause
                ), modifier = Modifier.align(alignment = Alignment.CenterVertically)) {
                    isPlaying.value = !isPlaying.value
                }

                ControlButton(
                    imageVector = vectorResource(id = R.drawable.ic_fast_forward),
                    modifier = Modifier.align (alignment = Alignment.CenterVertically)
                ) {}
            }
        }

        val songPlay = animatedValue(initVal = 0, converter = Int.VectorConverter)
        if(isPlaying.value) {
            songPlay.animateTo(targetValue = 3 * 60 * 1000, anim = tween(durationMillis = 3 * 60 * 1000, easing = LinearEasing))
        } else {
            songPlay.snapTo(targetValue = songPlay.value)
        }

        Disc(
                timeSpent = songPlay.value,
                totalTime = 3 * 60 * 1000,
                isPlaying = isPlaying.value,
                imageAsset = imageResource(id = R.drawable.alatreon_),
                modifier = Modifier.constrainAs(disc) {
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 24.dp)
                },
        )

    }
}

@Composable
fun ControlButton(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val isPressed = remember { mutableStateOf(false) }
    Box(
        modifier = modifier
            .clickable(
                onClick = onClick,
                indication = rememberRippleIndication(radius = 0.dp)
            )
            .pressIndicatorGestureFilter(
                onStart = { isPressed.value = true },
                onStop = { isPressed.value = false }
            )
    ) {
        Image(
            imageVector = imageVector,
            colorFilter = ColorFilter.tint(color = if(isPressed.value) NeomorphDarkColor else NeomorphColor),
        )
    }
}

@Composable
fun Disc(
        timeSpent: Int,
        totalTime: Int,
        isPlaying: Boolean,
        imageAsset: ImageBitmap,
        modifier: Modifier = Modifier,
) {
    val rotate = animate(
            target = if(isPlaying) 360f else 360f * (3000f/totalTime),
            animSpec = if(isPlaying) repeatable(
                    iterations = (totalTime - timeSpent)/3000,
                    animation = tween(durationMillis = 3000, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
            ) else snap())

    val size = animatedValue(initVal = 100.dp, converter = Dp.VectorConverter)

    size.animateTo(targetValue = if(isPlaying) 120.dp else 100.dp, anim = tween(durationMillis = 300))

    Card(modifier = modifier.size(size = size.value)
        .graphicsLayer(rotationZ = rotate),
            elevation = (size.value - 100.dp) * 1.5f,
            shape = CircleShape,
            backgroundColor = Color.Transparent
    ) {
        Image(bitmap = imageAsset,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
                .size(size = 200.dp)
        )
    }
}

@Preview
@Composable
fun previewMusicPlayer() {
    Box(modifier = Modifier.fillMaxSize().background(color = colorBg)) {
        MusicPlayer(
            modifier = Modifier.align(alignment = Alignment.BottomCenter)
        )
    }
}
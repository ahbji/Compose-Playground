package `in`.surajsau.tenji.musicplayer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.example.genshinloader.R

val colorBg = Color(0xFFF4DEE4)

@Composable
fun MusicPlayer(modifier: Modifier = Modifier) {

    ConstraintLayout(modifier = modifier
        .fillMaxWidth()
        .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)) {

        val (progressCard, playerCard, disc) = createRefs()

        Card(
            modifier = Modifier.fillMaxWidth(fraction = 0.9f)
                .offset(y = (-50).dp)
                .height(height = 100.dp)
                .constrainAs(progressCard) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            shape = RoundedCornerShape(size = 10.dp),
            backgroundColor = Color(0xA0FFFFFF),
            elevation = 0.dp,
        ){}

        Card(
            modifier = Modifier.fillMaxWidth()
                .height(height = 100.dp)
                .constrainAs(playerCard) {
                    bottom.linkTo(parent.bottom)
                },
            shape = RoundedCornerShape(size = 10.dp),
            elevation = 30.dp,
        ){}

        Disc(
                imageAsset = imageResource(id = R.drawable.alatreon_),
                modifier = Modifier.constrainAs(disc) {
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 24.dp)
                }
        )

    }
}

@Composable
fun Disc(
    imageAsset: ImageAsset,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.size(size = 100.dp)
        .clip(shape = CircleShape)
    ) {
        Image(asset = imageAsset,
            contentScale = ContentScale.Crop,
            modifier = Modifier.align(alignment = Alignment.Center)
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
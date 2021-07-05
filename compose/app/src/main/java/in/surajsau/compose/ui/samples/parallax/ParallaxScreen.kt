package `in`.surajsau.compose.ui.samples.parallax

import `in`.surajsau.compose.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val parallaxItems = listOf(
    ParallaxItem("Mizutsune", "タミツネ", R.drawable.mizutsune),
    ParallaxItem("Velkhana", "イヴェルカナ", R.drawable.velkhana),
    ParallaxItem("Fatalis", "ミラボレアス", R.drawable.fatalis_),
    ParallaxItem("Alatreon", "アルバトリオン", R.drawable.alatreon_),
    ParallaxItem("Nergigante", "ネルギガンテ", R.drawable.nergi),
)

@Composable
fun ParallaxScreen() {

    Box(modifier = Modifier.fillMaxSize()) {

        Parallax(modifier = Modifier.align(Alignment.CenterStart))
    }
}

@Composable
fun Parallax(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()

    Row(
        modifier = modifier
            .horizontalScroll(state = scrollState)
    ) {
        parallaxItems.forEach {
            ItemCard(item = it, scrollX = scrollState.value)
            Spacer(modifier = Modifier.width(width = 16.dp))
        }
    }
}

@Composable
fun ItemCard(
    item: ParallaxItem,
    scrollX: Int,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    Card(
        modifier = modifier
            .size(width = 320.dp, height = 500.dp)
            .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
            .clickable { onClick?.invoke() },
        elevation = 8.dp,
        shape = RoundedCornerShape(size = 8.dp),
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = item.imageId),
                contentDescription = item.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(fraction = 0.8f),
                alignment = BiasAlignment((scrollX / 1500f), 0f)
            )

            Text(text = item.title,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                modifier = Modifier.padding(start = 16.dp))

            Text(text = item.japanese,
                color = Color.Gray,
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 16.dp))
        }
    }
}

data class ParallaxItem(
    val title: String,
    val japanese: String,
    val imageId: Int
)

@Composable
@Preview
fun previewParallax() {
    rememberScrollState()
    Row(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {
        Parallax(
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
        )
    }
}
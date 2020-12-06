package `in`.surajsau.tenji.parallax

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.example.genshinloader.R

val items = listOf(
        Item("Mizutsune", "タミツネ", R.drawable.mizutsune),
        Item("Velkhana", "イヴェルカナ", R.drawable.velkhana),
        Item("Fatalis", "ミラボレアス", R.drawable.fatalis_),
        Item("Alatreon", "アルバトリオン", R.drawable.alatreon_),
        Item("Nergigante", "ネルギガンテ", R.drawable.nergi),
)

@Composable
fun Parallax(modifier: Modifier = Modifier) {
    val scrollX = remember { mutableStateOf(0f) }
    val scrollState = rememberScrollState()

    ScrollableRow(
            modifier = modifier,
            scrollState = scrollState
    ) {
        items.forEach {
            ItemCard(item = it, scrollX = scrollState.value) {

            }
        }
    }
}

@Composable
fun ItemCard(
        item: Item,
        scrollX: Float,
        modifier: Modifier = Modifier,
        onClick: (() -> Unit)? = null
) {
    Card(modifier = modifier.size(width = 320.dp, height = 500.dp)
            .padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
            elevation = 8.dp,
            shape = RoundedCornerShape(size = 8.dp),
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                    asset = imageResource(id = item.imageId),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                            .fillMaxHeight(fraction = 0.8f),
                    alignment = BiasAlignment((scrollX / 3000f), 0f)
            )
        }
    }
}

data class Item(
    val title: String,
    val japanese: String,
    val imageId: Int
)

@Composable
@Preview
fun previewParallax() {
    Row(modifier = Modifier.fillMaxSize().background(color = Color.White)) {
        Parallax(
                modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
        )
    }
}
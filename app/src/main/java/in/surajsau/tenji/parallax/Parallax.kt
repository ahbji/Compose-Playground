package `in`.surajsau.tenji.parallax

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val scrollState = rememberScrollState()

    ScrollableRow(
            modifier = modifier,
            scrollState = scrollState
    ) {
        items.forEach {
            ItemCard(item = it, scrollX = scrollState.value)
        }
        
        Spacer(modifier = Modifier.width(width = 16.dp))
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

data class Item(
    val title: String,
    val japanese: String,
    val imageId: Int
)

@Composable
@Preview
fun previewParallax() {
    rememberScrollState()
    Row(modifier = Modifier.fillMaxSize().background(color = Color.White)) {
        Parallax(
                modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
        )
    }
}
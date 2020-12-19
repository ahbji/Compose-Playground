package `in`.surajsau.compose.home

import `in`.surajsau.compose.androidx.AsyncImage
import `in`.surajsau.compose.data.ScreenData
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun ItemCard(
    modifier: Modifier = Modifier,
    item: ScreenData
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(
            topLeft = 8.dp.value,
            topRight = 8.dp.value,
            bottomLeft = 8.dp.value,
            bottomRight = 8.dp.value
        ),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                url = item.image,
                modifier = Modifier.fillMaxWidth()
                    .background(color = Color.LightGray)
                    .height(height = 100.dp)
            )
            Text(text = item.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp)
            )
            Text(text = item.author,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray,
                modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 16.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewItemCard() {
    ItemCard(
        item = ScreenData(
            id = "123",
            title = "title",
            url = "https://google.com",
            author = "author",
            image = "image",
            wip = false,
            type = "Dribble"
        ),
        modifier = Modifier.width(width = 250.dp)
    )
}
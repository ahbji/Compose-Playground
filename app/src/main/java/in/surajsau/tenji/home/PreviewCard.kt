package `in`.surajsau.tenji.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

data class Item(val title: String)

@Composable
fun ItemCard(item: Item) {
    Card(
            shape = RoundedCornerShape(
                topLeft = 8.dp.value,
                topRight = 8.dp.value,
                bottomLeft = 8.dp.value,
                bottomRight = 8.dp.value
            ),
    ) {
        Text(text = item.title,
            modifier = Modifier.padding(all = 16.dp))
    }
}

@Preview
@Composable
fun PreviewItemCard() {
    ItemCard(
            item = Item(title = "Title")
    )
}
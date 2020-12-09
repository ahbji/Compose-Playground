package `in`.surajsau.compose.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen() {
    val list = listOf(
            Item("Title1"),
            Item("Title2"),
            Item("Title3"),
            Item("Title4"),
            Item("Title5"),
            Item("Title6"),
            Item("Title7")
    )
    Row {
        LazyColumnFor(
                items = list.filterIndexed { index, _ -> index % 2 == 0 },
                modifier = Modifier
        ) { item ->
            ItemCard(item = item)
        }

        LazyColumnFor(
                items = list.filterIndexed { index, _ -> index % 2 == 1 },
                modifier = Modifier
        ) { item ->
            ItemCard(item = item)
        }
    }
}

@Composable
@Preview
private fun PreviewHomeScreen() {
    MaterialTheme {
        HomeScreen()
    }
}
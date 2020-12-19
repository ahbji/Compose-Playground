package `in`.surajsau.compose.home

import `in`.surajsau.compose.androidx.LazyGrid
import `in`.surajsau.compose.data.ScreenData
import `in`.surajsau.compose.data.ScreenDataRepository
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.onCommit
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    val list = remember { mutableStateListOf<ScreenData>() }

    onCommit {
        ScreenDataRepository().watchScreens {
            list.addAll(it)
        }
    }

    LazyGrid(items = list, rowSize = 2) { item ->
        ItemCard(item = item)
    }
}

@Composable
@Preview
private fun PreviewHomeScreen() {
    MaterialTheme {
        HomeScreen(modifier = Modifier.fillMaxSize())
    }
}
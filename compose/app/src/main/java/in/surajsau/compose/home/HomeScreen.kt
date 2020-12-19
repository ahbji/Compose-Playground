package `in`.surajsau.compose.home

import `in`.surajsau.compose.androidx.LazyGrid
import `in`.surajsau.compose.data.ScreenData
import `in`.surajsau.compose.data.ScreenDataRepository
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.onCommit
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(navController: NavController) {

    val list = remember { mutableStateListOf<ScreenData>() }

    onCommit {
        ScreenDataRepository().watchScreens {
            list.addAll(it)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyGrid(items = list, rowSize = 2) { item ->
            ItemCard(
                item = item,
                modifier = Modifier.clickable(onClick = {
                    navController.navigate(route = item.id)
                })
            )
        }
    }
}

@Composable
@Preview
private fun PreviewHomeScreen() {
    MaterialTheme {
        HomeScreen(rememberNavController())
    }
}
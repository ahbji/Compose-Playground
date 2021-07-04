package `in`.surajsau.compose.ui.screen

import `in`.surajsau.compose.model.Samples
import `in`.surajsau.compose.model.ScreenInformation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun Home(
    navigateToDetails: (ScreenInformation) -> Unit
) {

    val samples = remember { Samples }
}
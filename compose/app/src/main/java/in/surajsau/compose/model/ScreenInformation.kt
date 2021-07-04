package `in`.surajsau.compose.model

import androidx.compose.runtime.Composable

data class ScreenInformation(
    val title: String,
    val author: String,
    val source: String,
    val content: @Composable () -> Unit
)

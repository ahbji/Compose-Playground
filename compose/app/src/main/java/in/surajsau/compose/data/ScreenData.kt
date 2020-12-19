package `in`.surajsau.compose.data

data class ScreenData(
        val id: String,
        val title: String,
        val url: String,
        val author: String,
        val image: String,
        val wip: Boolean,
        private val type: String
)
package `in`.surajsau.compose.data

object Screens {
    val data = listOf(
            ScreenData("Todo List (Swipe To Check)",
                    "https://dribbble.com/shots/3959132-Todo-List-Swipe-To-Check",
                    "Gal Shir",
                    ""
            )
    )
}

data class ScreenData(
        val title: String,
        val url: String,
        val author: String,
        val imageUrl: String,
)
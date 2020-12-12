package `in`.surajsau.compose.todo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TodoList() {
    Column(modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
    ) {

        Text(text = "今日やること",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
        )

        LazyColumnFor(items =
            listOf(
                    "ミラボラスを倒す 🐉",
                    "ラージャンを倒す 🦍",
                    "猫様に餌あげる 🐈",
                    "テレビ見る 📺"
            )
        ) {
            TodoListItem(
                    text = it,
                    modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }
}

@Composable
@Preview
fun previewTodoList() {
    TodoList()
}
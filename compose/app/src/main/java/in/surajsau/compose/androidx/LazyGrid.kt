package `in`.surajsau.compose.androidx

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun <T> LazyGrid(
    items: List<T> = listOf(),
    rowSize: Int = 3,
    horizontalPadding: Dp = 8.dp,
    itemContent: @Composable LazyItemScope.(T) -> Unit
) {
    val chunkedList = items.chunked(rowSize)
    LazyColumnFor(
        items = chunkedList,
        modifier = Modifier.padding(horizontal = horizontalPadding)
    ) { row ->
        Row {
            row.forEach{ item ->
                Box(modifier = Modifier
                    .weight(1F)
                    .align(Alignment.Top)
                    .padding(8.dp)
                ) {
                    itemContent(item)
                }
            }
        }
    }
}

@Composable
fun <T> LazyGridIndexed(
    items: List<T> = listOf(),
    rowSize: Int = 3,
    horizontalPadding: Dp = 8.dp,
    itemContent: @Composable LazyItemScope.(T, Int) -> Unit
) {
    val chunkedList = items.chunked(rowSize)
    LazyColumnForIndexed(
        items = chunkedList,
        modifier = Modifier.padding(horizontal = horizontalPadding)
    ) { index, row ->
        Row {
            row.forEachIndexed { rowIndex, item ->
                Box(modifier = Modifier
                    .weight(1F)
                    .align(Alignment.Top)
                    .padding(8.dp)
                ) {
                    itemContent(item, (index * rowSize) + rowIndex)
                }
            }
        }
    }
}
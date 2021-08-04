package `in`.surajsau.compose.ui.samples.single

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex

fun <T> MutableList<T>.move(fromIndex: Int, toIndex: Int) {
    if (fromIndex == toIndex)
        return

    val element = this.removeAt(fromIndex)
    this.add(index = toIndex, element = element)
}

@Composable
fun ReorderListScreen() {

    val items = (0..5).map { ReorderItem(it) }.toMutableStateList()

    Box(modifier = Modifier.fillMaxSize()) {
        ReorderList(
            items = items,
            onMove = { from, to -> items.move(fromIndex = from, toIndex = to) },
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun rememberReorderableListState(
    lazyListState: LazyListState = rememberLazyListState(),
    onMove: (from: Int, to: Int) -> Unit
) = remember { ReorderableListState(lazyListState, onMove) }

class ReorderableListState constructor(
    val lazyListState: LazyListState,
    private val onMove: (fromIndex: Int, toIndex: Int) -> Unit,
) {

    private var selectedItem by mutableStateOf<LazyListItemInfo?>(null)

    private var draggedDistance by mutableStateOf(0f)

    var currentHoveredIndex by mutableStateOf<Int?>(null)

    private val currentHoveredItem: LazyListItemInfo?
        get() = currentHoveredIndex?.let { lazyListState.layoutInfo.visibleItemsInfo[it] }

    val offset: Float?
        get() = currentHoveredIndex
            ?.let { lazyListState.layoutInfo.visibleItemsInfo[it] }
            ?.let { (selectedItem?.offset?.toFloat() ?: 0f) + draggedDistance - it.offset }

    fun onDragStarted(offset: Offset) {
        lazyListState.layoutInfo.visibleItemsInfo
            .firstOrNull { offset.y.toInt() in it.offset..it.offsetEnd }
            ?.also {
                selectedItem = it
                currentHoveredIndex = it.index
            }
    }

    fun onDrag(offset: Offset) {
        draggedDistance += offset.y

        selectedItem?.let { item ->
            val startOffset = item.offset + draggedDistance
            val endOffset = startOffset + item.size

            currentHoveredItem?.let { hoveredItem ->
                lazyListState.layoutInfo.visibleItemsInfo
                    .filterNot { it.offsetEnd < startOffset || it.offset > endOffset || hoveredItem.index == it.index }
                    .firstOrNull { item ->
                        val delta = startOffset - hoveredItem.offset
                        when {
                            delta > 0 -> endOffset > item.offsetEnd
                            else -> startOffset < item.offset
                        }
                    }
                    ?.also {
                        currentHoveredIndex?.let { index -> onMove.invoke(index, it.index) }
                        currentHoveredIndex = it.index
                    }
            }
        }
    }

    fun onDragEnded() {
        selectedItem = null
        draggedDistance = 0f
        currentHoveredIndex = null
    }
}

val LazyListItemInfo.offsetEnd: Int
    get() = this.offset + this.size

val LazyListItemInfo.offsetCenter: Int
    get() = this.offset + (this.size / 2)

@Composable
fun Modifier.longPressReorderable(reorderableListState: ReorderableListState) = composed {
    return@composed Modifier.pointerInput(Unit) {
        detectDragGesturesAfterLongPress(
            onDragStart = { offset -> reorderableListState.onDragStarted(offset) },
            onDragEnd = { reorderableListState.onDragEnded() },
            onDrag = { change, offset ->
                change.consumeAllChanges()
                reorderableListState.onDrag(offset)
            },
        )
    }
}

sealed class ReorderableState(val zIndex: Float, val elevation: Float)
object NotReordered : ReorderableState(0f, 0f)
data class Reordered(val offset: Float) : ReorderableState(1f, 8f)

fun Modifier.reorderable(state: ReorderableState) = composed {
    return@composed Modifier
        .zIndex(state.zIndex)
        .graphicsLayer(
            translationY = (state as? Reordered)?.offset ?: 0f,
            shadowElevation = state.elevation,
        )
}

@Composable
fun ReorderList(
    items: List<ReorderItem>,
    onMove: (from: Int, to: Int) -> Unit,
    modifier: Modifier = Modifier
) {

    val state = rememberReorderableListState(onMove = onMove)

    LazyColumn(
        modifier = modifier
            .longPressReorderable(reorderableListState = state),
        contentPadding = PaddingValues(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(space = 8.dp),
        state = state.lazyListState
    ) {
        itemsIndexed(items) { idx, item ->
            Box(
                modifier = Modifier
                    .background(color = Color.White)
                    .fillMaxWidth()
                    .reorderable(
                        state = state.offset
                            ?.takeIf { idx == state.currentHoveredIndex }
                            ?.let { Reordered(it) }
                            ?: NotReordered
                    )
                    .border(
                        width = 0.5.dp,
                        color = Color.DarkGray,
                        shape = RoundedCornerShape(size = 4.dp)
                    ),
            ) {
                Text(
                    text = "Item ${item.id}",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(all = 16.dp)
                )
            }
        }
    }
}

@Stable
data class ReorderItem(val id: Int)

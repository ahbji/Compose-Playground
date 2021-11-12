package `in`.surajsau.compose.ui.samples.single

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ExpandableTextScreen(modifier: Modifier = Modifier) {

    Box(modifier = modifier) {
        ExpandableText(
            text = """
                Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has
                been the industry's standard dummy text ever since the 1500s, when an unknown printer took 
                a galley of type and scrambled it to make a type specimen book. It has survived not only 
                five centuries, but also the leap into electronic typesetting, remaining essentially 
                unchanged. It was popularised in the 1960s with the release of Letraset sheets 
                containing Lorem Ipsum passages, and more recently with desktop publishing software 
                like Aldus PageMaker including versions of Lorem Ipsum.
            """.trimIndent(),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(horizontal = 24.dp),
            isCompletelyClickableToExpand = true,
            isToggleable = true,
        )
    }
}

@Composable
fun ExpandableText(
    text: String,
    modifier: Modifier = Modifier,
    expandString: String = "...show more",
    collapseString: String = "...show less",
    collapseMaxLines: Int = 2,
    isToggleable: Boolean = false,
    isCompletelyClickableToExpand: Boolean = false,
) {
    var isExpanded by remember { mutableStateOf(false) }

    var modifiedText by remember { mutableStateOf(text) }

    var textLayoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }

    LaunchedEffect(textLayoutResult) {
        if (textLayoutResult == null)
            return@LaunchedEffect

        if (!isExpanded && textLayoutResult!!.hasVisualOverflow) {
            val endIndex = textLayoutResult!!.getLineEnd(lineIndex = collapseMaxLines - 1)
            val finalText = text
                .subSequence(startIndex = 0, endIndex = endIndex)
                .dropLast(expandString.length)

            modifiedText = "$finalText$expandString"
        } else if (isExpanded && isToggleable) {
            modifiedText = "$text$collapseString"
        }
    }

    Text(
        text = modifiedText,
        modifier = modifier
            .clickable(enabled = isCompletelyClickableToExpand) {
                if (isToggleable)
                    isExpanded = isExpanded.not()
                else {
                    if (!isExpanded)
                        isExpanded = true
                }
            },
        onTextLayout = { textLayoutResult = it },
        maxLines = if (isExpanded) Int.MAX_VALUE else collapseMaxLines
    )
}
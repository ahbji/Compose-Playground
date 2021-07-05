package `in`.surajsau.compose.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun Fab(
    icon: Int,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.secondary,
    onClick: (() -> Unit)? = null,
) {

    Box(
        modifier = modifier
            .size(size = 48.dp)
            .background(color = backgroundColor, shape = CircleShape)
            .clickable { onClick?.invoke() }
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "fab_icon",
            modifier = Modifier.align(alignment = Alignment.Center)
        )
    }
}
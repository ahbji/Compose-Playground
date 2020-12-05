package `in`.surajsau.tenji.spring

import `in`.surajsau.tenji.ui.defaultIconColor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview

object HillShape: Shape {
    override fun createOutline(size: Size, density: Density): Outline {
        val width = size.width
        val height = size.height

        return Outline.Generic(Path().apply {
            moveTo(0f, height)
            lineTo(width, height)
            lineTo(width, (height/20))
            lineTo((width * 3/4), 0f)
            lineTo((width * 1/2), 5f)
            lineTo(0f, (height * 1/5))
            close()
        })
    }
}

@Preview
@Composable
private fun previewHills() {
    Box(modifier = Modifier.fillMaxSize().background(color = defaultIconColor)) {
        Box(
                modifier = Modifier.fillMaxWidth()
                        .clip(shape = HillShape)
                        .height(height = 100.dp)
                        .background(color = Color(0xFF3F6274))
        )
    }
}
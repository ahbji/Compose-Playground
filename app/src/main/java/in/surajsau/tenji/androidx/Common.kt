package `in`.surajsau.tenji.androidx

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Dp

data class Size(val width: Dp, val height: Dp) {
    constructor(size: Dp) : this(width = size, height = size)

    fun toFloat() = androidx.compose.ui.geometry.Size(
            width = this.width.value,
            height = this.height.value
    )
}